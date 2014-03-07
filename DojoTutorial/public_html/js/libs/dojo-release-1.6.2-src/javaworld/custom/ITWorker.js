dojo.provide("javaworld.custom.ITWorker");

dojo.require("javaworld.custom.Worker");

//## Inheritance
//creates a custom.javaworld.ITWorker class that extends custom.javaworld.Worker.
dojo.declare("javaworld.custom.ITWorker",
        javaworld.custom.Worker,
        {
            constructor: function(fName, lName) {
                console.debug("Constructor of com.javaworld.ITWorker");
            },
            work: function() {
                /*
                 * Suppose you need to invoke the overridden method of superclass first and then add some functionality in the child class method. 
                 * In that case then you can use the.inherited() construct to invoke an inherited method of the superclass.
                 * */
                this.inherited(arguments);
                console.log("Writing Code");
            }
        }
);