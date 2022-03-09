/*
 * $Id: TooltipTest.java 439747 2006-09-03 09:22:46Z mrdon $
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
package org.apache.struts2.views.jsp.ui;

import org.apache.struts2.TestConfigurationProvider;
import org.apache.struts2.views.jsp.AbstractUITagTest;
import org.apache.struts2.views.jsp.ParamTag;
import org.apache.struts2.views.jsp.StrutsMockBodyContent;

import com.mockobjects.servlet.MockJspWriter;

/**
 * UI components Tooltip test case.
 */
public class TooltipTest extends AbstractUITagTest {

    public void testWithoutFormOverriding() throws Exception {

        configurationManager.clearConfigurationProviders();
        configurationManager.addConfigurationProvider(new TestConfigurationProvider());

        // we test it on textfield component, but since the tooltip are common to
        // all components, it will be the same for other components as well.
        FormTag formTag = new FormTag();
        formTag.setPageContext(pageContext);
        formTag.setId("myFormId");
        formTag.setAction("testAction");
        formTag.setName("myForm");


        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");


        tag.setTooltip("myTooltip");
        tag.setTooltipConfig(
                "#{" +
                        "'tooltipIcon':'/struts/tooltip/myTooltip.gif', " +
                        "'tooltipDelay':'500', " +
                        "'jsTooltipEnabled':'true' "+
                        "}"
        );

        formTag.doStartTag();
        tag.doStartTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-1.txt"));
    }

    public void testWithFormOverriding() throws Exception {

        configurationManager.clearConfigurationProviders();
        configurationManager.addConfigurationProvider(new TestConfigurationProvider());

        FormTag formTag = new FormTag();
        formTag.setPageContext(pageContext);
        formTag.setName("myForm");
        formTag.setId("myFormId");
        formTag.setAction("testAction");

        formTag.setTooltipConfig(
        		"#{" +
                "'tooltipIcon':'/struts/tooltip/myTooltip.gif', " +
                "'tooltipDelay':'500', " +
                "'jsTooltipEnabled':'true' "+
                "}"
        );


        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");

        tag.setTooltip("myTooltip");

        formTag.doStartTag();
        tag.doStartTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-2.txt"));
    }

    public void testWithPartialFormOverriding() throws Exception {

        configurationManager.clearConfigurationProviders();
        configurationManager.addConfigurationProvider(new TestConfigurationProvider());

        FormTag formTag = new FormTag();
        formTag.setName("myForm");
        formTag.setPageContext(pageContext);
        formTag.setId("myFormId");
        formTag.setAction("testAction");

        formTag.setTooltipConfig(
        		"#{" +
                "'tooltipIcon':'/struts/tooltip/myTooltip.gif', " +
                "'tooltipDelay':'500', " +
                "'jsTooltipEnabled':'true' "+
                "}"
        );


        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");

        tag.setTooltip("myTooltip");
        tag.setTooltipConfig(
        		"#{" +
                "'tooltipIcon':'/struts/tooltip/myTooltip2.gif', " +
                "'tooltipDelay':'5000' " +
                "}"
        );

        formTag.doStartTag();
        tag.doStartTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-3.txt"));
    }


    public void testUsingParamValueToSetConfigurations() throws Exception {
        configurationManager.clearConfigurationProviders();
        configurationManager.addConfigurationProvider(new TestConfigurationProvider());

        FormTag formTag = new FormTag();
        formTag.setName("myForm");
        formTag.setPageContext(pageContext);
        formTag.setId("myFormId");
        formTag.setAction("testAction");


        ParamTag formParamTag = new ParamTag();
        formParamTag.setPageContext(pageContext);
        formParamTag.setName("tooltipConfig");
        formParamTag.setValue(
        		"#{" +
                "'tooltipIcon':'/struts/tooltip/myTooltip.gif', " +
                "'tooltipDelay':'500', " +
                "'jsTooltipEnabled':'true' "+
                "}"
        );


        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");
        tag.setTooltip("myTooltip");

        ParamTag textFieldParamTag = new ParamTag();
        textFieldParamTag.setPageContext(pageContext);
        textFieldParamTag.setName("tooltipConfig");
        textFieldParamTag.setValue(
        		"#{" +
                "'tooltipIcon':'/struts/tooltip/myTooltip2.gif', " +
                "'tooltipDelay':'5000' "+
                "}"
        );

        formTag.doStartTag();
        formParamTag.doStartTag();
        formParamTag.doEndTag();
        tag.doStartTag();
        textFieldParamTag.doStartTag();
        textFieldParamTag.doEndTag();
        tag.doEndTag();
        formTag.doEndTag();

        verify(TooltipTest.class.getResource("tooltip-3.txt"));
    }


    public void testUsingParamBodyValueToSetConfigurations() throws Exception {
        configurationManager.clearConfigurationProviders();
        configurationManager.addConfigurationProvider(new TestConfigurationProvider());

        FormTag formTag = new FormTag();
        formTag.setName("myForm");
        formTag.setPageContext(pageContext);
        formTag.setId("myFormId");
        formTag.setAction("testAction");


        ParamTag formParamTag = new ParamTag();
        formParamTag.setPageContext(pageContext);
        formParamTag.setName("tooltipConfig");
        StrutsMockBodyContent bodyContent = new StrutsMockBodyContent(new MockJspWriter());
        bodyContent.setString(
                "tooltipIcon=/struts/tooltip/myTooltip.gif| " +
                "tooltipDelay=500| " +
                "jsTooltipEnabled=true "
        );
        formParamTag.setBodyContent(bodyContent);

        TextFieldTag tag = new TextFieldTag();
        tag.setPageContext(pageContext);
        tag.setLabel("MyLabel");
        tag.setId("myId");
        tag.setTooltip("myTooltip");


        ParamTag textFieldParamTag = new ParamTag();
        textFieldParamTag.setPageContext(pageContext);
        textFieldParamTag.setName("tooltipConfig");
        StrutsMockBodyContent bodyContent2 = new StrutsMockBodyContent(new MockJspWriter());
        bodyContent2.setString(
                "tooltipIcon=/struts/tooltip/myTooltip2.gif| " +
                "tooltipDelay=5000 "
        );
        textFieldParamTag.setBodyContent(bodyContent2);

        formTag.doStartTag();
        formParamTag.doStartTag();
        formParamTag.doEndTag();
        tag.doStartTag();
        textFieldParamTag.doStartTag();
        textFieldParamTag.doEndTag();
        tag.doEndTag();
        formTag.doEndTag();
        
        System.out.println(writer.toString());

        verify(TooltipTest.class.getResource("tooltip-3.txt"));
    }
}
