package raz.test.file.io.filescanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StringFileScanner {

	/**
	 * the purpose of this class is to find a String in .txt file
	 * 
	 * @throws IOException
	 * */

	public static void main(String[] args) throws IOException {

		

		File f = new File("src/main/java/raz/test/file/io/filescanner/"
				+ "20130207040731_stdIPAD_20130207_morgen.zip.txt");
		System.out.println(">>" + f.getCanonicalPath());
		
		long start = System.currentTimeMillis();
		CotainsStringUsingFileScanner(f, "ApaError");
		long execTime = System.currentTimeMillis() - start;
		System.out.println("Execution time in milis:"+execTime);

	}

	public static void PrintEveryWordUsingFileScanner(File f) {
		Scanner s = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			s = new Scanner(br);

			while (s.hasNext()) {
				System.out.println(s.next());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
	
	/**
	 * Vreau sa vad daca un anume string se afla intr-un fisier text
	 * */
	public static void CotainsStringUsingFileScanner(File f, String searchedFor) {
		Scanner s = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			s = new Scanner(br);
			
			s.useDelimiter(searchedFor);
			
			while(s.hasNext()){
				System.out.println(s.next());
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
			}
		}
	}
	
	

}
