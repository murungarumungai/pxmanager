package org.eclipse.ui.articles.views;

import org.eclipse.jface.viewers.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.*;
import org.eclipse.ui.articles.views.data.Word;
import org.eclipse.ui.part.ViewPart;

/**
 * No view is an island. In most cases, a view co-exists with other views and
 * selection within one view may affect the input of another. In this section
 * we'll create a Listener view, which will listen for the selection of objects
 * in the Word view. If a Word object is selected, the Listener view will
 * display the word attributes. This behavior is similar to the existing
 * Properties view in the workbench standard components. -- To start out, we
 * need to create a new Listener view. In createPartControl we create a simple
 * SWT Label , where we will display the word attributes.
 * */

// Razvan: Ce se intampla daca nu declar ListenerView in plugin.xml?
/*
 * Can I Declare A View Through API? Every view within the workbench must be
 * declared in XML. There are two reasons for this.
 */

public class ListenerView extends ViewPart implements ISelectionListener {

	private Label label;

	public ListenerView() {
		super();
	}

	public void setFocus() {
		label.setFocus();
	}

	public void createPartControl(Composite parent) {
		label = new Label(parent, 0);
		label.setText("Hello World");
		/*
		 * The Listener view exists in a page with other views. If a selection
		 * is made in any of those views, the platform will forward the
		 * selection event to all interested parties. We are an interested
		 * party. To register our interest, we get the site, get the page, and
		 * add the ListenerView as a selection listener. If a selection occurs
		 * within the page, the ListenerView.selectionChanged method will be
		 * called. Within this method, the selection is examined and, if it is a
		 * Word, the label text will be updated to reflect the Word name.
		 */
		getViewSite().getPage().addSelectionListener(this);
	}

	/**
	 * @see ISelectionListener#selectionChanged(IWorkbenchPart, ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Object first = ((IStructuredSelection) selection).getFirstElement();
			if (first instanceof Word) {
				label.setText(((Word) first).toString());
			}
		}
	}
}
