package org.eclipse.ui.articles.views;

import org.eclipse.ui.articles.views.data.Word;

public interface Listener {

	public void added(Word w);

	public void removed(Word w);

}
