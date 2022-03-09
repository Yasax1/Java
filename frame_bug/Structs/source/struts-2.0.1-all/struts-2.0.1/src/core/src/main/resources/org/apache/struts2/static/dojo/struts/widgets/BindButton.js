dojo.provide("struts.widgets.BindButton");
dojo.provide("struts.widgets.HTMLBindButton");

dojo.require("dojo.io.*");
dojo.require("dojo.event.*");
dojo.require("dojo.widget.*");
dojo.require("dojo.xml.Parse");

dojo.require("struts.Util");
dojo.require("struts.widgets.HTMLBind");

/*
 * Component to do a remote submit of a HTML form.
 */

struts.widgets.HTMLBindButton = function() {

	// inheritance
    // see: http://www.cs.rit.edu/~atk/JavaScript/manuals/jsobj/
	struts.widgets.HTMLBind.call(this);
	var self = this;

	this.widgetType = "BindButton";
	this.templatePath = dojo.uri.dojoUri("struts/widgets/BindButton.html");

    // dom node in the template that will contain the remote content
	this.attachBtn = null;

    //a snippet of js to invode before binding
    this.preInvokeJS = "";

    var super_fillInTemplate = this.fillInTemplate;
	this.fillInTemplate = function(args, frag) {
		super_fillInTemplate(args, frag);

       if (self.id) {
			self.attachBtn.id = self.id;
		}

		struts.Util.passThroughArgs(self.extraArgs, self.attachBtn);
	}

    this.execute = function() {
        var executeConnect = true;

        //If the user provided some preInvokeJS invoke it and store the results into the
        //executeConnect var to determine if the connect should occur
		if (self.preInvokeJS != "") {
            dojo.debug('Evaluating js: ' + this.preInvokeJS);
            executeConnect = eval(this.preInvokeJS);
		}
        if (executeConnect) {
            try {
                this.bind();
            } catch (e) {
                dojo.debug("EXCEPTION: " + e);

            }
        }
    }
}

dojo.inherits(struts.widgets.HTMLBindButton, struts.widgets.HTMLBind);

// make it a tag
dojo.widget.tags.addParseTreeHandler("dojo:BindButton");

// HACK - register this module as a widget package - to be replaced when dojo implements a propper widget namspace manager
dojo.widget.manager.registerWidgetPackage('struts.widgets');
