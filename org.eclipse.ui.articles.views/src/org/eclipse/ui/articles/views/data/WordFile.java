package org.eclipse.ui.articles.views.data;

import java.io.*;
import java.util.*;

import org.eclipse.ui.articles.views.Listener;

public class WordFile {

	private File file;
	private List<Word> wordList = new ArrayList<Word>();
	private Listener listener;

	

	/**
	 * Constructor for FileList
	 */
	public WordFile(File file) {
		this.file = file;
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
			for (int nX = 0; nX < size; nX++) {
				String str = data.readUTF();
				wordList.add(new Word(str));
			}
			data.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
