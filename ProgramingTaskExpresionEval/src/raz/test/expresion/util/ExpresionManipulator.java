/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.expresion.util;

import raz.test.command.CommandInvoker;

/**
 *
 * @author razvang
 */
public class ExpresionManipulator {

    /**
     * This method removes the <b>eval</b> key-word from the string
     * @return returns the String without <b>eval</b>
     */
    public static String removeEvalKeyWord(String expresion) {
        String result = null;
        result = expresion.substring(Assert.EVAL.length(), expresion.length());
        System.out.println("1.removeEvalKeyWord"+result);
        return result;
    }

    /**
     * This method removes the round Parenthesis from the string
     * @return the String without ()
     */
    public static String removeParenthesis(String expresion) {
        String result = null;
        int beginIndex = expresion.indexOf(Assert.ROUND_PARENTHESIS_BEGIN);
        if (!(beginIndex == 0)) {
            //Pozitia pe care ar trebui sa fie ( ar trebui sa fie 0
            throw new IllegalStateException("( - was not found at the begining of the Expresion");
        }
        int endIndex = expresion.indexOf(Assert.ROUND_PARENTHESIS_END);
        if (!(endIndex == expresion.length() - 1)) {
            //Pozitia pe care ar trebui sa fie ) ar trebui sa fie expresion.length - 1
            throw new IllegalStateException(") - was not found at the end of the Expresion");
        }
        result = expresion.substring(beginIndex+1, endIndex);
        System.out.println("2.removeParenthesis"+result);
        return result;
    }
    /**
     * 
     * This method splits the expresion into a String[]
     */
    public static String[] splitStringByComma(String expresion){
        String[] result = expresion.split(",");
        if(null!=result){
            for(String oneStr:result){
                System.out.println(">>"+oneStr);
            }
        } else {
            System.out.println("Split result was null");
        }
        return result;
    }


    /**
     * Am nevoie de o metoda care primeste o functie si parametri si:
     * determina ce functie este:
     * - trim
     * - concat
     * - substr
     * si o executa, eventual se poate implementa cu un Command Pattern pentru ca seamana a command patern
     * adica pregateste comanda si o executa
     */
}
