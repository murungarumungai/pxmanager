package org.eclipse.ui.articles.views.action;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.articles.views.WordView;
import org.eclipse.ui.articles.views.data.Word;

import raz.test.utils.Utils;

public class ActionDelete extends Action {

	WordView view;

	public ActionDelete(String text, WordView pView) {
		super(text);
		view = pView;
		setImageDescriptor(Utils.getImageDescriptor("delete.gif"));
	}

	@Override
	public void run() {
		deleteItem();
	}

	/**
	 * Remove item from list.
	 */
	private void deleteItem() {
		IStructuredSelection sel = (IStructuredSelection) view.getViewViewer()
				.getSelection();
		Iterator iter = sel.iterator();
		while (iter.hasNext()) {
			Word word = (Word) iter.next();
			// input.remove(word);
			view.getViewDataModel().remove(word);
		}
	}

}
