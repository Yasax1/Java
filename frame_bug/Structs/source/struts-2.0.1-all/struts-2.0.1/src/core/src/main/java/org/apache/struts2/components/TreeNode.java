/*
 * $Id: TreeNode.java 451544 2006-09-30 05:38:02Z mrdon $
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * <!-- START SNIPPET: javadoc -->
 *
 * Renders a tree node within a tree widget with AJAX support.<p/>
 *
 * Either of the following combinations should be used depending on if the tree 
 * is to be constrcted dynamically or statically. <p/>
 * 
 * <b>Dynamically</b>
 * <ul>
 * 		<li>id - id of this tree node</li>
 * 		<li>title - label to be displayed for this tree node</li>
 * </ul>
 * 
 * <b>Statically</b>
 * <ul>
 * 		<li>rootNode - the parent node of which this tree is derived from</li>
 * 		<li>nodeIdProperty - property to obtained this current tree node's id</li>
 * 		<li>nodeTitleProperty - property to obtained this current tree node's title</li>
 * 		<li>childCollectionProperty - property that returnds this current tree node's children</li>
 * </ul>
 *
 * <!-- END SNIPPET: javadoc -->
 *
 * <p/> <b>Examples</b>
 *
 * <pre>
 * <!-- START SNIPPET: example -->
 * 
 * &lt-- statically --&gt;
 * &lt;s:tree id="..." label="..."&gt;
 *    &lt;s:treenode id="..." label="..." /&gt;
 *    &lt;s:treenode id="..." label="..."&gt;
 *        &lt;s:treenode id="..." label="..." /&gt;
 *        &lt;s:treenode id="..." label="..." /&gt;
 *    &;lt;/s:treenode&gt;
 *    &lt;s:treenode id="..." label="..." /&gt;
 * &lt;/s:tree&gt;
 * 
 * &lt;-- dynamically --&gt;
 * &lt;s:tree
 *          id="..."
 *          rootNode="..."
 *          nodeIdProperty="..."
 *          nodeTitleProperty="..."
 *          childCollectionProperty="..." /&gt;
 * 
 * <!-- END SNIPPET: example -->
 * </pre>
 *
 *
 * @s.tag name="treenode" tld-body-content="JSP" tld-tag-class="org.apache.struts2.views.jsp.ui.TreeNodeTag"
 * description="Render a tree node within a tree widget."
 */
public class TreeNode extends ClosingUIBean {
    private static final String TEMPLATE = "treenode-close";
    private static final String OPEN_TEMPLATE = "treenode";

    public TreeNode(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
        super(stack, request, response);
    }

    public String getDefaultOpenTemplate() {
        return OPEN_TEMPLATE;
    }

    protected String getDefaultTemplate() {
        return TEMPLATE;
    }

    /**
     * Label expression used for rendering tree node label.
     * @s.tagattribute required="true"
     */
    public void setLabel(String label) {
        super.setLabel(label);
    }
}
