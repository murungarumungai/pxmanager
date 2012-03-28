package org.eclipse.examples.helloworld;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class HelloWorldAction implements IWorkbenchWindowActionDelegate {

	IWorkbenchWindow activeWindow = null;

	@Override
	public void run(IAction action) {
		// proxyAction has UI information from manifest file (ignored)
		Shell shell = activeWindow.getShell();
		MessageDialog.openInformation(shell, "Hello World", "Hello World!");
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// do nothing, action is not dependent on the selection
	}

	@Override
	public void dispose() {
		// nothing to do
	}

	@Override
	public void init(IWorkbenchWindow window) {
		activeWindow = window;
	}

}
