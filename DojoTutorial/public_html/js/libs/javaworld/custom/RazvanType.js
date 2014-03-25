dojo.provide("javaworld.custom.RazvanType");

dojo.declare("javaworld.custom.RazvanType",
        null, {
            name: "default",
            isAssigned: false,
            
            constructor: function(params){
                if(params){
                    this.params = params;
                    dojo._mixin(this, params);
                }
            },
            
            check: function() {
                console.debug(">>"+this.name+"<<");
                if (this.isAssigned) {
                    console.debug("TRUE");
                }
                if (!this.isAssigned) {
                    console.debug("FALSE");
                }
            }
        });