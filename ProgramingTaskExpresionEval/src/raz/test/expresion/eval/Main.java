/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.expresion.eval;

import raz.test.command.CommandInvoker;
import raz.test.expresion.util.Assert;
import raz.test.expresion.util.ExpresionManipulator;

/**
 *
 * @author razvan
 *
 * "eval(trim, abc defg)" va intoarce "abc defg"
 * "eval(concat, abc , de, fg)" va intoarce "abcdefg"
 */
public class Main {

    public static void main(String[] args) {
        if (null != args) {
            System.out.println("Argument size:" + args.length);
            String inputExpresion = stringArrayToString(args);
            System.out.println("Input:" + inputExpresion + ":");
            String output = executeCommand(inputExpresion);
            System.out.println("Output:"+output+":");
        } else {
            System.out.println("Argument was null");
        }
    }

    /**
     * Plec de la ideea ca eu parsez un String ... input
     * si atunci am nevoie de o metoda care ia argumentele main si le transforma intr-un String
     */
    public static String stringArrayToString(String[] input) {
        String result = "";
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Argument can not be null or empty");
        } else if (input.length == 1) {
            result = input[0];
        } else {

            String space = " ";
            for (int i = 0; i < input.length; i++) {
                result = result + input[i];
                /*
                 * Adauga spatiu dupa fiecare element din array, incepand cu primul,
                 * dar nu adauga spatiu si dupa ultimul.
                 * Indexul Ultimului element este: input.length-1
                 */
                if (i < input.length - 1) {
                    result = result + space;
                }
            }
        }
        return result;
    }

    /**
     * Am novie de o functie care parseaza Stringul expresie si il sparge in
     * "atomi lexicali" dupa virgula ","
     * @param expresion the expresion to be interpreted
     *
     */
    public static String parseExpresion(String expresion) {
        String parsedExpresion = null;
        //Primul pas, trebe sa validez ca expresia nu este nulla
        Assert.assertNotNull(expresion, "expresion can not be null");
        //si ca incepe cu eval
        Assert.startsWithEval(expresion, "expresion does not start with 'eval'");
        //incep parsarea
        //1.substrag primul key-word eval
        parsedExpresion = ExpresionManipulator.removeEvalKeyWord(expresion);
        //2.si o sa am '(trim, param)' - inlatur paranteleze ()
        //parsedExpresion = ExpresionManipulator.removeParenthesis(parsedExpresion);
        //3. o sa am 'trim, abc defg'
        System.out.println("Parsed expresion<<"+parsedExpresion+">>");
        String[] expresionArray = ExpresionManipulator.splitStringByComma(parsedExpresion);
        //4. Executa fiecare expresie. Nu executa expresia ci doar intoarce un commandInvoker?
        CommandInvoker c = new CommandInvoker(expresionArray);
        //System.out.println("Final Result:"+c.executeCommand());
        return c.executeCommand();
    }

    public static String executeCommand(String expresion){
        String result = null;
        result = parseExpresion(expresion);
        return result;
    } 
}
