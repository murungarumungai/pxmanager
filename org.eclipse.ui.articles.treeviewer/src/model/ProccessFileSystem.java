package model;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class ProccessFileSystem {

	public static void main(String args[]) {

		for (File root : File.listRoots()) {
			// out.format("%s ", root);

			// getChildren(root, null);

			// Start Processing the FileSystem
			System.out.println(root + " " + root.getAbsolutePath());
			if (root.getAbsolutePath().contains("X")) {
				TreeRoot oneRoot = new TreeRoot(root);
				oneRoot.processChildren();
			}
		}

	}

	/**
	 * Gets all the children of this file If file is a File and not a dir we
	 * return null
	 * 
	 * @param file
	 *            must be a valid File Path on the FileSystem
	 * */
	static List<File> getChildren(File file, File parent) {
		List<File> children = null;

		if (file.isDirectory()) {
			children = Arrays.asList(file.listFiles());
			if (children == null || children.isEmpty()) {
				// stop recursion
				// we have an empty Directory add it to the parent
			} else {
				for (File oneFile : children) {
					if (oneFile.exists()) {
						return getChildren(oneFile, file);
					}
				}
			}
		} else {
			// file is File, stop recursion
			// add this to the parent
		}

		return children;
	}

}
