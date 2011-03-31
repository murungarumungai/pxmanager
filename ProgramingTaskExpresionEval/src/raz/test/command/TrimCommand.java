/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.command;

/**
 *
 * @author razvang
 *
 * SPECS:
 * trim este o functie cu un singur parametru care elimina spatiitle de la inceputul si sfarsitul parametrului
 *
 */
public class TrimCommand implements Command {

    public TrimCommand(String param) {
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    private String param;
    private String result;

    /**
     * Returns a copy of the string, with leading and trailing whitespace omitted.
     */
    public void execute() {
        result = param.trim();
    }
}
