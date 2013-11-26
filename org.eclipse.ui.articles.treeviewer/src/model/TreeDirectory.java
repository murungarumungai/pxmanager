package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TreeDirectory extends AbstractTreeFile {

	private List<AbstractTreeFile> children;

	public TreeDirectory(File path, TreeDirectory parent) {

		super(path, parent);
		children = new ArrayList<AbstractTreeFile>();
	}

	/**
	 * Converts this file into a Directory
	 * */
	public TreeDirectory(AbstractTreeFile file) {
		super(file.getPath(), file.getParent());
		children = new ArrayList<AbstractTreeFile>();
	}

	/**
	 * Use this constructor only for Root
	 * */
	protected TreeDirectory(File root) {
		super(root);
		children = new ArrayList<AbstractTreeFile>();
	}

	public List<AbstractTreeFile> getChildren() {
		return children;

	}

	public void setChildren(List<AbstractTreeFile> children) {
		this.children = children;
	}

	@Override
	public void processChildren() {

		if (path.listFiles() != null) {
			if (path.listFiles().length == 0) {
				// I am a folder and I have no children.
				// I only have to add myself as a child to my parent
				parent.getChildren().add(this);
			} else {
				parent.getChildren().add(this);
				// I have children I have to process them
				for (File file : path.listFiles()) {

					if (file.exists()) {// Make sure the file exists

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
