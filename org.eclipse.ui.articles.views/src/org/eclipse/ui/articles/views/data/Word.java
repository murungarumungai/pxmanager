package org.eclipse.ui.articles.views.data;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IActionFilter;
import org.eclipse.ui.articles.views.WordActionFilter;

public class Word implements IAdaptable {

	private String str;
	
	public Word(String str) {
		super();
		this.str = str;
	}
	
	public String toString() {
		return str;
	}

	public Object getAdapter(Class adapter) {
		if (adapter == IActionFilter.class) {
			return WordActionFilter.getSingleton();
		}
		return null;
	}

}

