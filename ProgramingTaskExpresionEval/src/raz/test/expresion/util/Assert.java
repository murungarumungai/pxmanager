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

    public static final String EVAL = "eval";
    public static final String ROUND_PARENTHESIS_BEGIN = "(";
    public static final String ROUND_PARENTHESIS_END = ")";
    public static final String TRIM = "trim";
    public static final String CONCAT = "concat";
    public static final String SUBSTR = "substr";

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
