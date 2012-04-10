package org.eclipse.ui.articles.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		/*
		 * layout.addView() adds a view to the perspective. You can also add
		 * placeholders for views via the layout.addPlaceholder() method call.
		 */
//		layout.addView("org.eclipse.ui.articles.views.labelview",
//				IPageLayout.TOP, IPageLayout.RATIO_MAX,
//				IPageLayout.ID_EDITOR_AREA);
	}
}
