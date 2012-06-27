//http://groovy.codehaus.org/Quick+Start
//http://groovy.codehaus.org/Closures+-+Informal+Guide
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raz.test.groovy

//---Working with Closures
//Closures are similar to Java's inner classes, except they are a single method which is invokable, 
//with arbitrary parameters. A closure can have as many parameters as you wish...
def closure = "String Closure";
println closure;

//Definesc un inner class
closure = {
    param -> println("hello ${param} !")
}

closure.call("world");

//Parameters are specified before symbol "->"
closure = {
    greeting, name -> println(greeting +name)
}

closure.call("hello","world");

//If no parameters are specified before -> symbol, then a default parameter called "it" can be used

closure = { print "hello" + it}

closure.call("world!");
