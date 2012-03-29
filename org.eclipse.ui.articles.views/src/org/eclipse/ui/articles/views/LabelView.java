package org.eclipse.ui.articles.views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class LabelView extends ViewPart {
	private Label label;

	public LabelView() {
		super();
	}

	/*
	 * The layout of editors and views within a page is controlled by the active
	 * perspective. You can think of a perspective as a layout containing views,
	 * folders, and place holders. A perspective is a visual container for a set
	 * of views and editors (parts). These parts exist wholly within the
	 * perspective and are not shared. A perspective is also like a page within
	 * a book. It exists within a window along with any number of other
	 * perspectives and, like a page within a book, only one perspective is
	 * visible at any time. Each perspective has an input and a type. The input
	 * attribute is used to define which resources are visible in the workspace
	 * and the type attribute is used to define which actions and views are
	 * visible in the user interface.
	 */

	/*
	 * Within this method the view can create any number of SWT controls within
	 * a parent Composite.
	 */
	@Override
	public void createPartControl(Composite parent) {
		label = new Label(parent, 0);
		label.setText("Hello World");
	}

	@Override
	public void setFocus() {
		label.setFocus();

	}

}
