package org.eclipse.ui.articles.views;

//http://www.eclipse.org/articles/viewArticle/ViewArticle2.html

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.articles.views.data.WordFile;
import org.eclipse.ui.part.ViewPart;

public class WordView extends ViewPart {

	WordFile input;
	ListViewer viewer;
	Action addItemAction;
	Action deleteItemAction;
	Action selectAllAction;
	IMemento memento;

	public WordView() {
		super();
		input = new WordFile(new File("list.lst"));
	}

	/*
	 * The createPartControl method is called to create a SWT Control for the
	 * WordFile model In the Word view the model is a simple list of words, so a
	 * ListViewer is used for the presentation.
	 */
	@Override
	public void createPartControl(Composite parent) {
		// Create viewer.
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
		//restoreState();

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
