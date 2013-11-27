package raz.test.filesystem.explorer.ui;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

import raz.test.filesystem.explorer.AbstractTreeFile;
import raz.test.filesystem.explorer.TreeDirectory;

public class TreeFileContentProvider implements ITreeContentProvider {

	public static Object[] EMPTY_ARRAY = new Object[0];
	protected TreeViewer viewer;

	/**
	 * The input changed method caches the viewer argument for later use when
	 * responding to events.
	 * 
	 * the inputChanged method will be called when the tree viewer's setInput
	 * method is invoked.
	 * 
	 * When the tree viewer's input is changed, we need to make sure that we
	 * remove any listeners we have associated with the old input so that we no
	 * longer receive updates from the stale input. Likewise, we need to listen
	 * for changes in the new input.
	 * */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.viewer = (TreeViewer) viewer;

	}

	@Override
	public Object[] getChildren(Object parent) {
		Object[] children = EMPTY_ARRAY;
		if (parent instanceof TreeDirectory) {
			children = ((TreeDirectory) parent).getChildren().toArray();
		}
		return children;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	public Object getParent(Object child) {
		Object parent = null;
		if(child instanceof AbstractTreeFile) {
			((AbstractTreeFile) child).getParent();
		}
		return parent;
	}

	@Override
	public boolean hasChildren(Object parent) {
		return getChildren(parent).length > 0;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
