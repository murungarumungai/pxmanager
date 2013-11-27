package raz.test.filesystem.explorer.ui;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class FileLabelProvider extends LabelProvider {

	@Override
	public String getText(Object element) {
		if (element instanceof File) {
			String label = "";
			if (((File) element).getName().isEmpty()) {
				label = ((File) element).getPath();
			} else {
				label = ((File) element).getName();
			}
			return label;
		} else {
			throw TreeFileLabelProvider.unknownElement(element);
		}
	}

	public Image getImage(Object obj) {
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		if (obj instanceof File) {
			if (((File) obj).isDirectory()) {
				imageKey = ISharedImages.IMG_OBJ_FOLDER;

			}
			return PlatformUI.getWorkbench().getSharedImages()
					.getImage(imageKey);
		} else {
			throw TreeFileLabelProvider.unknownElement(obj);
		}
	}

}
