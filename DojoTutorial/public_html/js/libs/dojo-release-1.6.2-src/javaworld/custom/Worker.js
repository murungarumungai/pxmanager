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
//## Multiple inheritance
//Dojo allows a class to inherit from multiple classes. Listing 5 creates a PartTimeMusician class that inherits from both ITWorker and Musician.
dojo.declare("javaworld.custom.Musician", null,
        {
            constructor: function(fName, lName) {
                console.debug("Constructor of com.javaworld.Musician");
            },
            playMusic: function() {
                console.log("Playing Music");
            }
        }
);
dojo.declare("javaworld.custom.PartTimeMusician",
        [javaworld.custom.ITWorker, javaworld.custom.Musician],
        {
            constructor: function(fName, lName) {
                console.debug("Constructor of com.javaworld.PartTimeMusician");
            }
        }
);