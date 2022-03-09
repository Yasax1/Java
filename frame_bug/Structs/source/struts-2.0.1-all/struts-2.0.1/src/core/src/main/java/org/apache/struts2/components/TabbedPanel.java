/*
 * $Id: TabbedPanel.java 451544 2006-09-30 05:38:02Z mrdon $
 *
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.struts2.components;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * <!-- START SNIPPET: javadoc -->
 * The tabbedpanel widget is primarily an AJAX component, where each tab can either be local content or remote
 * content (refreshed each time the user selects that tab).</p>
 * <!-- END SNIPPET: javadoc -->
 *
 * <p/> <b>Examples</b>
 * <p/>
 * <!-- START SNIPPET: exdesc -->
 * The following is an example of a tabbedpanel and panel tag utilizing local and remote content.<p/>
 * <!-- END SNIPPET: exdesc -->
 * <pre>
 * <!-- START SNIPPET: example -->
 * &lt;s:tabbedPanel id="test2" theme="simple" &gt;
 *     &lt;s:panel id="left" tabName="left" theme="ajax"&gt;
 *         This is the left pane&lt;br/&gt;
 *         &lt;s:form &gt;
 *             &lt;s:textfield name="tt" label="Test Text" /&gt;  &lt;br/&gt;
 *             &lt;s:textfield name="tt2" label="Test Text2" /&gt;
 *         &lt;/s:form&gt;
 *     &lt;/s:panel&gt;
 *     &lt;s:panel remote="true" href="/AjaxTest.action" id="ryh1" theme="ajax" tabName="remote one" /&gt;
 *     &lt;s:panel id="middle" tabName="middle" theme="ajax"&gt;
 *         middle tab&lt;br/&gt;
 *         &lt;s:form &gt;
 *             &lt;s:textfield name="tt" label="Test Text44" /&gt;  &lt;br/&gt;
 *             &lt;s:textfield name="tt2" label="Test Text442" /&gt;
 *         &lt;/s:form&gt;
 *     &lt;/s:panel&gt;
 *     &lt;s:panel remote="true" href="/AjaxTest.action"  id="ryh21" theme="ajax" tabName="remote right" /&gt;
 * &lt;/s:tabbedPanel&gt;
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 * <p/> <b>Additional Configuration</b>
 *
 * <!-- START SNIPPET: exdesc2 -->
 * If you are looking for the "nifty" rounded corner look, there is additional configuration. This assumes
 * that the background color of the tabs is white. If you are using a different color, please modify the
 * parameter in the Rounded() method.<p/>
 * <!-- END SNIPPET: exdesc2 -->
 *
 * <pre>
 * <!-- START SNIPPET: example2 -->
 * &lt;link rel="stylesheet" type="text/css" href="&lt;s:url value="/struts/tabs.css"/&gt;"&gt;
 * &lt;link rel="stylesheet" type="text/css" href="&lt;s:url value="/struts/niftycorners/niftyCorners.css"/&gt;"&gt;
 * &lt;link rel="stylesheet" type="text/css" href="&lt;s:url value="/struts/niftycorners/niftyPrint.css"/&gt;" media="print"&gt;
 * &lt;script type="text/javascript" src="&lt;s:url value="/struts/niftycorners/nifty.js"/&gt;"&gt;&lt;/script&gt;
 * &lt;script type="text/javascript"&gt;
 *     dojo.event.connect(window, "onload", function() {
 *         if (!NiftyCheck())
 *             return;
 *         Rounded("li.tab_selected", "top", "white", "transparent", "border #ffffffS");
 *         Rounded("li.tab_unselected", "top", "white", "transparent", "border #ffffffS");
 *         // "white" needs to be replaced with the background color
 *     });
 * &lt;/script&gt;
 * <!-- END SNIPPET: example2 -->
 * </pre>
 *
 * <b>Important:</b> Be sure to setup the page containing this tag to be Configured for AJAX
 *
 * @see Panel
 *
 * @s.tag name="tabbedPanel" tld-body-content="JSP" tld-tag-class="org.apache.struts2.views.jsp.ui.TabbedPanelTag"
 * description="Render a tabbedPanel widget."
 */
public class TabbedPanel extends ClosingUIBean {
    public static final String TEMPLATE = "tabbedpanel";
    public static final String TEMPLATE_CLOSE = "tabbedpanel-close";
    final private static String COMPONENT_NAME = TabbedPanel.class.getName();

    protected List tabs = new ArrayList();

    public TabbedPanel(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    /**
     * Add a new panel to be rendered.
     *
     * @param pane a new panel to be rendered
     */
    public void addTab(Panel pane) {
        tabs.add(pane);
    }

    /**
     * Get the list of panel tabs for this tab panel.
     *
     * @return the list of panel tabs for this tab panel
     */
    public List getTabs() {
        return tabs;
    }

    public String getTopicName() {
        return "topic_tab_" + id + "_selected";
    }

    protected void evaluateExtraParams() {
        super.evaluateExtraParams();

        addParameter("topicName", "topic_tab_" + id + "_selected");
        addParameter("tabs", tabs);

    }

    public String getDefaultOpenTemplate() {
        return TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE_CLOSE;
    }

    public String getComponentName() {
        return COMPONENT_NAME;
    }

    /**
     * The id to assign to the component.
     * @s.tagattribute required="true" type="String"
     */
    public void setId(String id) {
        // This is required to override tld generation attributes to required=true
        super.setId(id);
    }
}
