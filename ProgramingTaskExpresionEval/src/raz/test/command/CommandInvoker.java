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
public  class CommandInvoker {

    private String[] command;
    private Command c;

    public CommandInvoker(String[] command) {
        this.command = command;

        if (command[0].equalsIgnoreCase(Assert.CONCAT)) {
            c = new ConcatCommand();
        } else if (command[0].equalsIgnoreCase(Assert.TRIM)) {
             c = new TrimCommand(command[1]);
        } else if(command[0].equalsIgnoreCase(Assert.SUBSTR)){
            String source = command[1];
            int beginIndex = Integer.parseInt(command[2]);
            int numberOfCharsToBeSubtracted = Integer.parseInt(command[3]);
            c = new SubstrCommand(source, beginIndex, numberOfCharsToBeSubtracted);
        }
        else {
            throw new IllegalArgumentException("Command Unknown:" + command[0]);
        }
    }

    

    public String executeTrimCommand(){
        TrimCommand trim = (TrimCommand) c;
        trim.execute();
        return trim.getResult();
    }

    
}
