/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raz.test.command;

/**
 *
 * @author razvang
 * SPEC:
 * substr este o functie cu 3 parametri in care:
 * primul parametru reprezinta Stringul din care se va extrage
 * cel de-al 2-lea parametru reprezinta pozitia de unde se va incepe extragerea
 * cel de-al 3-lea parametru reprezinta numarul de caractere care vor fi extrase
 */
public class SubstrCommand implements Command {

    private String source;
    private int beginIndex;
    private int subtractCharNo;
    private String result;

    public SubstrCommand(String source, int beginIndex, int subtractCharNo) {
        this.source = source;
        this.beginIndex = beginIndex;
        this.subtractCharNo = subtractCharNo;
    }

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getResult() {
        return result;
    }
}
