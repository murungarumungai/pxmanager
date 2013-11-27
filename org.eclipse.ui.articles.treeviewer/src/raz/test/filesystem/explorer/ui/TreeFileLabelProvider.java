package raz.test.filesystem.explorer.ui;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import raz.test.filesystem.explorer.AbstractTreeFile;
import raz.test.filesystem.explorer.TreeDirectory;

public class TreeFileLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof AbstractTreeFile) {
			String label = "";
			if (((AbstractTreeFile) element).getPath().getName().isEmpty()) {
				label = ((AbstractTreeFile) element).getPath().getPath();
			} else {
				label = ((AbstractTreeFile) element).getPath().getName();
			}
			return label;
		} else {
			throw unknownElement(element);
		}
	}

	private IllegalArgumentException unknownElement(Object element) {
		return new IllegalArgumentException(
				"Unknown type of element in tree of type "
						+ element.getClass().getName());
	}

	public Image getImage(Object obj) {
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		if (obj instanceof TreeDirectory)
			imageKey = ISharedImages.IMG_OBJ_FOLDER;
		return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
	}

}
