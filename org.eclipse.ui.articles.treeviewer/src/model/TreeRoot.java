package model;

import java.io.File;
import java.util.List;

public class TreeRoot extends TreeDirectory {

	public TreeRoot(File path) {
		super(path);
	}

	@Override
	public void processChildren() {

		if (path.listFiles() != null) {
			if (path.listFiles().length == 0) {
				// I have no children.
				// I am root, nothing to do
			} else {
				for (File file : path.listFiles()) {

					if (file.exists()) {//Make sure the file exists
						
						AbstractTreeFile child = null;

						if (file.isDirectory()) {
							// The Child is a directory
							child = new TreeDirectory(file, this);
							child.processChildren();
						} else {
							// The child is a file
							child = new TreeFile(file, this);
							child.processChildren();
						}
					}
				}
			}
		}

	}

}
