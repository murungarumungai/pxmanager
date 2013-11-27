package raz.test.filesystem.views;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import raz.test.filesystem.explorer.ui.FileContentProvider;
import raz.test.filesystem.explorer.ui.FileLabelProvider;

public class FileExplorerView extends ViewPart {

	private TreeViewer treeViewer;

	public FileExplorerView() {

	}

	@Override
	public void createPartControl(Composite parent) {

		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);

		treeViewer.setContentProvider(new FileContentProvider());
		treeViewer.setLabelProvider(new FileLabelProvider());

		treeViewer.setInput(getInitialInput());

	}

	private List<File> getInitialInput() {

		List<File> roots = new ArrayList<File>();

		for (File root : File.listRoots()) {
			// out.format("%s ", root);

			// getChildren(root, null);

			// Start Processing the FileSystem
			System.out.println(root + " " + root.getAbsolutePath());
			if (/*root.getAbsolutePath().contains("X")*/ true) {
				roots.add(root);
			}
		}

		return roots;
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();

	}

}
