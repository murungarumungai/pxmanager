/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * SPEC:
 * concat este o functie care poate avea oricati parametri si realizeaza concatenarea parametrilor
 */
package raz.test.command;

import java.util.ArrayList;
import java.util.List;
import raz.test.expresion.util.Assert;

/**
 *
 * @author razvang
 */
public class ConcatCommand implements Command {

    private String result = "";

    public ConcatCommand(String... params) {
        Assert.assertNotNull(params, "Parameters to be concatenated can not null");
        if (params.length == 0) {
            throw new IllegalArgumentException("Numer of Parameters to be concatenated must be at lest 1");
        }

        for (String oneString : params) {

            result = result + oneString;
        }
    }

    public void execute() {
    }

    public String getResult() {
        return result;
    }
}
