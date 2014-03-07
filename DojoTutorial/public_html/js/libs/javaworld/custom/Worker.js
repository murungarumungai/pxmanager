dojo.provide("javaworld.custom.Worker");
//Dojo provides a dojo.declare() method that you should use to declare a new class. 
dojo.declare("javaworld.custom.Worker",
        null,
        {//The third argument is a hashmap, in which work is the property's name and its value is a function.
            firstName: "",
            lastName: "",
            constructor: function(fName, lName) {
                console.debug("Constructor of com.javaworld.Worker called");
                this.firstName = fName;
                this.lastName = lName;
            },
            /*
             * work is the property's name and its value is a function. 
             * We thereby define a work() member function for the Worker class.
             */
            work: function() {
                console.log("Working");
            }
        }
);

