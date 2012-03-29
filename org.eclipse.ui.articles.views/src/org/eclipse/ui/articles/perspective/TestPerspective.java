package org.eclipse.ui.articles.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/*
 * 
 A new IWorkbenchPage object is created with perspective id = "org.eclipse.ui.articles.perspective.Test".
 The perspective id is used to lookup the actual perspective extension in the plugin registry.  
 Within the workbench a list of the registered perspective extensions are stored within the IPerspectiveRegistry (available from the IWorkbench interface).  
 The findPerspectiveWithId method is called to get a complete description of the perspective.  This method returns an object of type IPerspectiveDescriptor.
 The perspective class is retrieved from the IPerspectiveDescriptor.  This class must implement IPerspectiveFactory.
 An instance of the perspective class is created, yielding a IPerspectiveFactory.
 The createInitialLayout method is called on the IPerspectiveFactory.  This method defines the initial layout for a page. 
 Implementors may add views, folders, actions and action sets to the page layout.
 The IPerspectiveFactory is dereferenced and is not used again during the life cycle of the page.
 The IWorkbenchPage is activated.
 * */

public class TestPerspective implements IPerspectiveFactory {

	/*
	 * When createInitialLayout is called on an IPerspectiveFactory the page
	 * layout consists of an editor area with no additional views.
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);

	}

	public void defineActions(IPageLayout layout) {
		// Add "new wizards".
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
		layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");

		// Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_RES_NAV);
		layout.addShowViewShortcut(IPageLayout.ID_BOOKMARKS);
		layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
		layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
		layout.addShowViewShortcut(IPageLayout.ID_TASK_LIST);
	}
	
	public void defineLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();

        // Place navigator and outline to left of
        // editor area.
		IFolderLayout left =
                layout.createFolder("left", IPageLayout.LEFT, (float) 0.26, editorArea);
        left.addView(IPageLayout.ID_RES_NAV);
        left.addView(IPageLayout.ID_OUTLINE);
}

}
