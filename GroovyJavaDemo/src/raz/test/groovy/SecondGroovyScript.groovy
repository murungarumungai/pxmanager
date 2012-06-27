/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package raz.test.groovy

def name='rgaston'

println "Hello $name!"

int justAnInt = 10;
println "Displaying an integer: $justAnInt!";

//Weak type? FirstClass Objects?
//Probably the easiest way to get groovy is to try working with collections. 
//In Groovy List (java.util.List) and Map (java.util.Map) are both first class objects in the syntax. 
def list = [1, 2, 'hello', new java.util.Date()];
assert list.size() == 4;
assert list.get(2) == 'hello';
assert list[2] == 'hello';
//Iterating over collections is easy...
for( i in list) {
    println i
}



