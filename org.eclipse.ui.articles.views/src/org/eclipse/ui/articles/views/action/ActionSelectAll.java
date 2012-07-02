package org.eclipse.ui.articles.views.action;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.articles.views.WordView;

public class ActionSelectAll extends Action {
	
	
	WordView view;
	
	public ActionSelectAll(String text, WordView pView){
		//call the Superclass constructor
		super(text);
		view = pView;
	}
	
	public void run() {
		selectAll();
	}
	
	/**
	 * Select all items.
	 */
	private void selectAll() {
		view.getViewViewer().getList().selectAll();
		view.updateActionEnablement();
	}

}
