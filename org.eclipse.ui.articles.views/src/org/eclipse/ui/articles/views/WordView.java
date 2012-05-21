package org.eclipse.ui.articles.views;

//http://www.eclipse.org/articles/viewArticle/ViewArticle2.html

import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;
import  java.net.URL;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.articles.views.content.provider.WordContentProvider;
import org.eclipse.ui.articles.views.data.WordFile;
import org.eclipse.ui.part.ViewPart;



/*
 * After the view is instantiated, the IViewPart.init method is called with an
 * IViewSite (). The site is the primary interface between the view part and the
 * outside world. Given the site, you can access the view menu, toolbar, status
 * line, containing page, containing window, shell, etc. In the code above we
 * simply call the superclass, ViewPart, where the site is stored in an instance
 * variable. It can be retrieved at any time by calling getViewSite().
 * */
public class WordView extends ViewPart {

	WordFile input;
	ListViewer viewer;
	Action addItemAction;
	Action deleteItemAction;
	Action selectAllAction;
	IMemento memento;

	public WordView() {
		super();
		
		
		try {
			input = new WordFile("list.lst");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	/*
	 * The createPartControl method is called to create a SWT Control for the
	 * WordFile model. In the Word view the model is a simple list of words, so
	 * a ListViewer is used for the presentation.
	 */
	@Override
	public void createPartControl(Composite parent) {
		// Create viewer.
		/*
		 * A viewer is a wrapper for a SWT control, adding a model based
		 * interface to it. (the Model Based interface is
		 * IStructuredContentProvider)
		 */
		viewer = new ListViewer(parent);
		/*
		 * A content provider is an adapter for a domain specific model,
		 * wrapping it in an abstract interface which the viewer invokes to get
		 * the model root and its children. If the model (the WordFile) changes,
		 * the content provider will also refresh the state of the ListViewer to
		 * make the changes visible.
		 */
		viewer.setContentProvider(new WordContentProvider());
		/*
		 * A label provider is also defined. This serves up a label and image
		 * for each object which is supplied by the content provider.
		 */
		viewer.setLabelProvider(new LabelProvider());
		/*
		 * And finally, we set the input for the ListViewer. The input is just
		 * the WordFile created in the WordView constructor.
		 */
		viewer.setInput(input);

		/*
		 * The createPartControl method also contains several method calls for
		 * the creation of a menu, toolbar, context menu, and global actions --
		 * each view has a local menu and toolbar which appear in the view title
		 * area. The view may define a context menu (pop-up menu) for each
		 * control it creates, or hook a global action in the parent window.
		 */
		// Create menu and toolbars.
		createActions();
		createMenu();
		createToolbar();
		createContextMenu();
		hookGlobalActions();

		// Restore state from the previous session.
		// restoreState();

	}

	private void hookGlobalActions() {
		// TODO Auto-generated method stub

	}

	private void createContextMenu() {
		// TODO Auto-generated method stub

	}

	private void createToolbar() {
		// TODO Auto-generated method stub

	}

	private void createMenu() {
		// TODO Auto-generated method stub

	}

	private void createActions() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();

	}

}
