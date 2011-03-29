/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.expresion.util;

/**
 *
 * @author razvang
 */
public class Assert {

    public static String EVAL = "eval";
    public static String ROUND_PARENTHESIS_BEGIN = "(";
    public static String ROUND_PARENTHESIS_END = ")";

    public static void assertNotNull(Object subject, String message) {
        if (null == subject) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void startsWithEval(String expresion, String message) {
        if (!expresion.startsWith(EVAL)) {
            throw new IllegalArgumentException(message);
        }
    }
}
