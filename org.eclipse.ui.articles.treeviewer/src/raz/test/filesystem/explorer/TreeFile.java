package raz.test.filesystem.explorer;

import java.io.File;

public class TreeFile extends AbstractTreeFile {

	public TreeFile(File path, TreeDirectory parent) {
		super(path, parent);
		
	}

	@Override
	public void processChildren() {
		
		// path is File, stop recursion
		// add this File to the Children of its parent
		
		//u are a file, add yourself as a child to your parent
		parent.getChildren().add(this);
	}

}
