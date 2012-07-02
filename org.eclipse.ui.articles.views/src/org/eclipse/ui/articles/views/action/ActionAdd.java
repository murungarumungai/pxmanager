package org.eclipse.ui.articles.views.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.articles.views.WordView;
import org.eclipse.ui.articles.views.data.Word;

import raz.test.utils.Utils;

public class ActionAdd extends Action {
	
	WordView view;
	
	
	public  ActionAdd(String text, WordView pView){
		super(text);
		view = pView;
		setImageDescriptor(Utils.getImageDescriptor("add.gif"));
	}
	
	@Override
	public void run() {
		addItem();
	}
	
	/**
	 * Add item to list.
	 */
	private void addItem() {
		String name = promptForValue("Enter name:", null);
		if (name != null) {
			Word word = new Word(name);
			view.getViewDataModel().add(word);
			view.getViewViewer().setSelection(new StructuredSelection(word));
		}
	}
	
	/**
	 * Ask user for value.
	 */
	private String promptForValue(String text, String oldValue) {
		InputDialog dlg = new InputDialog(view.getSite().getShell(), "List View",
				text, oldValue, null);
		if (dlg.open() != Window.OK)
			return null;
		return dlg.getValue();
	}

}
