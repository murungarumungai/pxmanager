/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.command;

import raz.test.expresion.util.Assert;

/**
 *
 * @author razvang
 *
 * this class will execute a command
 */
public class CommandInvoker {

    private Command c;

    public CommandInvoker(String[] command) {

        c = getInstance(command);

    }

    private static Command getInstance(String[] command) {
        Command c;
        if (command[0].equalsIgnoreCase(Assert.CONCAT)) {
            /*
             * daca comand este concat atunci, command[0] = concat
             * restul de la [1]...[end] o sa fie parametri.
             * deci trebuie sa-mi fac un nou string incepand de la comman[1]...[command.length-1]
             * foloseste System.arrayCopy
             */
            int length = command.length-1;
            String[] concatParams = new String[length]; 
            System.arraycopy(command, 1, concatParams, 0, length);
            c = new ConcatCommand(concatParams);
            return c;
        } else if (command[0].equalsIgnoreCase(Assert.TRIM)) {
            c = new TrimCommand(command[1]);
            return c;
        } else if (command[0].equalsIgnoreCase(Assert.SUBSTR)) {
            String source = command[1];
            int beginIndex = Integer.parseInt(command[2]);
            int numberOfCharsToBeSubtracted = Integer.parseInt(command[3]);
            c = new SubstrCommand(source, beginIndex, numberOfCharsToBeSubtracted);
            return c;
        } else {
            throw new IllegalArgumentException("Command Unknown:" + command[0]);
        }
    }

    public String executeCommand() {

        c.execute();
        return c.getResult();
    }
}
