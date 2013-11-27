package raz.test.filesystem.explorer;

import java.io.File;

public abstract class AbstractTreeFile {

	protected final File path;
	protected final TreeDirectory parent;
	
	/**
	 * This constructor should be used only for ROOT
	 * */
	public AbstractTreeFile(File path){
		this(path, null);
	}

	public AbstractTreeFile(File path, TreeDirectory parent) {

		//if (path.exists()) {

			this.path = path;
			this.parent = parent;
		//} 
			/*else {
			throw new IllegalArgumentException("File Path:" + path
					+ "Does not Exist");
		}*/
	}

	/**
	 * - processes recursively the Children of this Directory
	 * */
	
	public abstract void processChildren();
	
//	public void processChildren() {
//		List<File> children = null;
//
//		if (path.isDirectory()) {
//			//This is a folder, so convert it into a Folder
//			TreeDirectory thisDir = new TreeDirectory(this);
//			
//			//Get the Children of this Directory
//			children = Arrays.asList(path.listFiles());
//			
//			if (children == null || children.isEmpty()) {
//				// stop recursion
//				//we have an empty Directory add it to the parent
//				parent.getChildren().add(thisDir);
//			} else {
//				
//				for (File oneFile : children) {
//					if (oneFile.exists()) {
//						AbstractTreeFile child = new AbstractTreeFile(oneFile, thisDir);
//						//process the Children Recursively
//						child.processChildren();
//					}
//				}
//				
//			}
//			
//		} else {
//			// path is File, stop recursion
//			// add this File to the Children of its parent
//			parent.getChildren().add(this);
//		}
//	}

	public File getPath() {
		return path;
	}

	public TreeDirectory getParent() {
		return parent;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		sb.append(path.getAbsolutePath());
		sb.append("]");
		sb.append("[");
		sb.append(parent!=null ? parent.getPath() : "null");
		sb.append("]");
		return sb.toString();
	}

}
