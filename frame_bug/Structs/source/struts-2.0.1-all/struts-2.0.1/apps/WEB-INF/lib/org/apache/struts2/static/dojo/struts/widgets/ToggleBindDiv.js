dojo.provide("struts.widgets.ToggleBindDiv");
dojo.provide("struts.widgets.HTMLToggleBindDiv");

dojo.require("dojo.io.*");
dojo.require("dojo.event.*");
dojo.require("dojo.widget.*");
dojo.require("dojo.xml.Parse");

dojo.require("struts.Util");
dojo.require("struts.widgets.HTMLBindDiv");

/*
 * Component to do remote updating of a DOM tree.
 */

struts.widgets.HTMLToggleBindDiv = function() {

	struts.widgets.HTMLBindDiv.call(this);
	var self = this;

	this.widgetType = "ToggleBindDiv";

	// support a toggelable div - each listenEvent will trigger a change in the display state
	// the bind call will only happen when the remote div is displayed
	this.toggle2 = false;

	var super_fillInTemplate = this.fillInTemplate;
	this.fillInTemplate = function(args, frag) {
		super_fillInTemplate(args, frag);
	
	   	if (self.toggle2) {
			dojo.event.kwConnect({
				type: 'around',
				srcObj: self,
				srcFunc: "bind",
				adviceObj: self,
				adviceFunc: "__subclassToggleInterceptor"
			});
    	}

	}
	
	this.__subclassToggleInterceptor = function(invocation) {
		var hidden = self.contentDiv.style.display == 'none';
		self.contentDiv.style.display = (hidden)?'':'none';
		if (hidden) {
			invocation.proceed();
		}
	}

}
dojo.inherits(struts.widgets.HTMLToggleBindDiv, struts.widgets.HTMLBindDiv);

// make it a tag
dojo.widget.tags.addParseTreeHandler("dojo:ToggleBindDiv");

// HACK - register this module as a widget package - to be replaced when dojo implements a propper widget namspace manager
dojo.widget.manager.registerWidgetPackage('struts.widgets');
