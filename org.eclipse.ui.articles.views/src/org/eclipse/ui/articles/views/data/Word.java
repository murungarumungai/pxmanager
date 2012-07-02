package org.eclipse.ui.articles.views.data;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.articles.views.action.filter.WordActionFilter;

public class Word implements IAdaptable {

	private String str;

	public Word(String str) {
		super();
		this.str = str;
	}

	public String toString() {
		return str;
	}

	/**
	 * The action filter for the Word class will be exposed using the IAdaptable
	 * mechanism. To do this, we need to implement the IAdaptable interface on
	 * our Word object, and return the WordActionFilter singleton if the
	 * platform asks for an IActionFilter. The implementation of getAdapter is
	 * shown below.
	 * */
	public Object getAdapter(Class adapter) {
		if (adapter == IActionFilter.class) {
			return WordActionFilter.getSingleton();
		}
		return null;
	}

}
