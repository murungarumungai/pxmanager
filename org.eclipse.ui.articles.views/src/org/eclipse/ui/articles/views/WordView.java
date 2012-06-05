package org.eclipse.ui.articles.views;

//http://www.eclipse.org/articles/viewArticle/ViewArticle2.html

import org.eclipse.ui.articles.views.data.Word;

import java.io.File;
import java.io.IOException;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.articles.views.content.provider.WordContentProvider;
import org.eclipse.ui.articles.views.data.WordFile;
import org.eclipse.ui.part.ViewPart;
import raz.test.utils.Utils;

/*
 * After the view is instantiated, the IViewPart.init method is called with an
 * IViewSite (). The site is the primary interface between the view part and the
 * outside world. Given the site, you can access the view menu, toolbar, status
 * line, containing page, containing window, shell, etc. In the code above we
 * simply call the superclass, ViewPart, where the site is stored in an instance
 * variable. It can be retrieved at any time by calling getViewSite().
 * */
public class WordView extends ViewPart {

	WordFile input;// The DataModel, a simple List of words

	ListViewer viewer;// ListViewer, the View to represent the Model

	// The Actions
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
		 * A content provider is an adapter for a Domain Specific Model,
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
		/*
		 * A global action refers to those actions in the window menu and
		 * toolbar which are always visible, but delegate their implementation
		 * to the active part. For instance, the Cut, Copy and Paste actions are
		 * always visible in the Edit menu. If invoked, their implementation is
		 * delegated to the active part.
		 */
		hookGlobalActions();

		// Restore state from the previous session.
		restoreState();

	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();

	}

	/**
	 * Create the Actions
	 */
	private void createActions() {
		// Add----
		addItemAction = new Action("Add...") {
			public void run() {
				addItem();
			}
		};
		addItemAction.setImageDescriptor(Utils.getImageDescriptor("add.gif"));
		// Delete-----
		deleteItemAction = new Action("Delete") {
			public void run() {
				deleteItem();
			}
		};
		deleteItemAction.setImageDescriptor(Utils
				.getImageDescriptor("delete.gif"));
		// Select All---
		selectAllAction = new Action("Select All") {
			public void run() {
				selectAll();
			}
		};

		// Add selection listener.
		/*
		 * In general, the enablement state of a menu or tool item should
		 * reflect the view selection. If a selection occurs in the Word view,
		 * the enablement state of each action will be updated in the
		 * updateActionEnablement method
		 */
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				updateActionEnablement();
			}
		});

	}

	/**
	 * Create menu.
	 */
	private void createMenu() {
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
		mgr.add(selectAllAction);
	}

	/**
	 * Create toolbar.
	 */
	private void createToolbar() {
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		mgr.add(addItemAction);
		mgr.add(deleteItemAction);
	}

	/**
	 * Create context menu.
	 */
	private void createContextMenu() {
		// Create menu manager.
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager mgr) {
				fillContextMenu(mgr);
			}
		});

		// Create menu.
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);

		// Register menu for extension.
		/*
		 * After creating the MenuManager, we can create the menu, and then
		 * register the menu manager with the site.
		 */
		getSite().registerContextMenu(menuMgr, viewer);
	}

	private void fillContextMenu(IMenuManager mgr) {
		mgr.add(addItemAction);
		mgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		mgr.add(deleteItemAction);
		mgr.add(new Separator());
		mgr.add(selectAllAction);
	}

	/**
	 * Hook global actions
	 */
	private void hookGlobalActions() {
		IActionBars bars = getViewSite().getActionBars();
		bars.setGlobalActionHandler(IWorkbenchActionConstants.SELECT_ALL,
				selectAllAction);
		bars.setGlobalActionHandler(IWorkbenchActionConstants.DELETE,
				deleteItemAction);
		viewer.getControl().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent event) {
				if (event.character == SWT.DEL && event.stateMask == 0
						&& deleteItemAction.isEnabled()) {
					deleteItemAction.run();
				}
			}
		});
	}

	private void updateActionEnablement() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		deleteItemAction.setEnabled(sel.size() > 0);
	}

	// -----------------
	/**
	 * Add item to list.
	 */
	private void addItem() {
		String name = promptForValue("Enter name:", null);
		if (name != null) {
			Word word = new Word(name);
			input.add(word);
			viewer.setSelection(new StructuredSelection(word));
		}
	}

	/**
	 * Remove item from list.
	 */
	private void deleteItem() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
		Iterator iter = sel.iterator();
		while (iter.hasNext()) {
			Word word = (Word) iter.next();
			input.remove(word);
		}
	}

	/**
	 * Select all items.
	 */
	private void selectAll() {
		viewer.getList().selectAll();
		updateActionEnablement();
	}

	/**
	 * Ask user for value.
	 */
	private String promptForValue(String text, String oldValue) {
		InputDialog dlg = new InputDialog(getSite().getShell(), "List View",
				text, oldValue, null);
		if (dlg.open() != Window.OK)
			return null;
		return dlg.getValue();
	}

	// ------------------------
	/**
	 * Restores the viewer state from the memento.
	 */
	private void restoreState() {
		if (memento == null)
			return;
		memento = memento.getChild("selection");
		if (memento != null) {
			IMemento descriptors[] = memento.getChildren("descriptor");
			if (descriptors.length > 0) {
				ArrayList objList = new ArrayList(descriptors.length);
				for (int nX = 0; nX < descriptors.length; nX++) {
					String id = descriptors[nX].getID();
					Word word = input.find(id);
					if (word != null)
						objList.add(word);
				}
				viewer.setSelection(new StructuredSelection(objList));
			}
		}
		memento = null;
		updateActionEnablement();
	}

}
