package raz.test.filesystem.explorer.ui;

import java.io.File;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FileContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		Object[] children = TreeFileContentProvider.EMPTY_ARRAY;
		if (parentElement instanceof File
				&& ((File) parentElement).isDirectory() && ((File) parentElement).listFiles()!=null) {
			children = ((File) parentElement).listFiles();
		} else if (parentElement instanceof List){
			return ((List) parentElement).toArray();
		}
		return children;
	}

	@Override
	public Object getParent(Object element) {
		Object parent = null;
		if(element instanceof File) {
			parent = ((File) element).getParentFile();
		}
		return parent;
	}

	@Override
	public boolean hasChildren(Object element) {
		
		return getChildren(element).length > 0;
	}

}
