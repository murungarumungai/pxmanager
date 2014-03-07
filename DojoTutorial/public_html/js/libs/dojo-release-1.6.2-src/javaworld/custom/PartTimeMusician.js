dojo.provide("javaworld.custom.PartTimeMusician");

//A module can import code that it depends on using a dojo.require() call.
dojo.require("javaworld.custom.Musician");
dojo.require("javaworld.custom.ITWorker");

//## Multiple inheritance
//Dojo allows a class to inherit from multiple classes. Listing 5 creates a PartTimeMusician class that inherits from both ITWorker and Musician.

dojo.declare("javaworld.custom.PartTimeMusician",
        [javaworld.custom.ITWorker, javaworld.custom.Musician],
        {
            constructor: function(fName, lName) {
                console.debug("Constructor of com.javaworld.PartTimeMusician");
            }
        }
);