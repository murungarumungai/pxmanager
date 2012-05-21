package org.eclipse.ui.articles.views.data;

import java.io.*;
import java.net.URL;
import java.util.*;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.articles.views.Listener;

public class WordFile {

	private File file;
	private List<Word> wordList = new ArrayList<Word>();
	private Listener listener;

	/**
	 * Constructor for FileList
	 * @throws IOException 
	 */
	public WordFile(String path) throws IOException {
		URL fileURL = readFile(path);
		this.file = new File(fileURL.getPath());
		if (file.exists()) {
			readFile();
		} else {
			writeFile();
		}
	}

	public void setListener(Listener l) {
		listener = l;
	}

	public void add(Word word) {
		wordList.add(word);
		writeFile();
		if (listener != null)
			listener.added(word);
	}

	public void remove(Word word) {
		wordList.remove(word);
		writeFile();
		if (listener != null)
			listener.removed(word);
	}

	public Word find(String str) {
		Iterator iter = wordList.iterator();
		while (iter.hasNext()) {
			Word word = (Word) iter.next();
			if (str.equals(word.toString()))
				return word;
		}
		return null;
	}

	public List<Word> elements() {
		return wordList;
	}

	private void writeFile() {
		try {
			OutputStream os = new FileOutputStream(file);
			DataOutputStream data = new DataOutputStream(os);
			data.writeInt(wordList.size());
			Iterator iter = wordList.iterator();
			while (iter.hasNext()) {
				Word word = (Word) iter.next();
				data.writeUTF(word.toString());
			}
			data.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readFile() {
		try {
			InputStream is = new FileInputStream(file);
			DataInputStream data = new DataInputStream(is);
			int size = data.readInt();
			BufferedReader br = new BufferedReader(new InputStreamReader(data));
			String oneLine = null;
			while( (oneLine = br.readLine()) != null) {
				 
				wordList.add(new Word(oneLine));
			}
			data.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read a file path inside an eclipse plugin
	 * */
	public static URL readFile(String path) throws IOException {
		URL fullPathString = FileLocator.find(Platform.getBundle("org.eclipse.ui.articles.views"), new Path(
				path), null);
		return FileLocator.resolve(fullPathString);
		

	}

}
