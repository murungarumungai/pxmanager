dojo.provide("javaworld.custom.Musician");

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