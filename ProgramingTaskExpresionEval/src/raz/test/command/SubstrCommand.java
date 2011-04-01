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
 * indexul/pozitia 1 reprezinta primul caracter
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
        int length = source.length();
        if (beginIndex > length) {
            //Nu pot sa incep extragerea daca indexul de unde se incepe extragerea este mai mare decat ultimul index din String
            //ultimul index din String este... lastIndex = source.length() -1
            throw new IllegalArgumentException("BeginIndex is bigger than the last index in the source. " + "beginIndex:" + beginIndex + " lastIndex:" + (length - 1));
        }
        if (subtractCharNo <= 0) {
            //Numarul de caractere care trebuiesc extrase trebuie sa fie cel putin 1
            throw new IllegalArgumentException("Number of characters to be subtracted is invalid. subtractCharNo:" + subtractCharNo);
        }
        StringBuffer rez = new StringBuffer(subtractCharNo);
        char[] charArray = source.toCharArray();

        int numarDeCaractereExtrase = 1;
        /*
         * In Java Stringul incepe de la index zero
         * "abc" = [0]a,[1]b,[2]c
         */
        int javaIndex = beginIndex - 1;

        while (numarDeCaractereExtrase <= subtractCharNo) {
            rez.append(charArray[javaIndex]);
            numarDeCaractereExtrase++;
            javaIndex++;
        }

        result = rez.toString();
    }

    public String getResult() {
        return result;
    }
}
