/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.expresion.eval;

/**
 *
 * @author razvang
 */
public class Factorial {

    public static void main(String[] args) {
        System.out.println("Calculam n! Factorialul unui nr");
        int input = 4;
        long output = factorial(input);
        System.out.println("Factorial de:"+input+"="+output);
    }

    public static long factorial(int n) {
        long result = 1;
        if (n == 0) {
            result = 1;
            //contita de stop
        } else {
            //conditia de recursivitate
            result = n * factorial(n - 1);
        }
        return result;
    }
}
