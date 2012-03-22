package de.vogella.rcp.intro.commands.keybinding;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class HelloHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		System.out.println("Hello");
		MessageDialog.openInformation(
				HandlerUtil.getActiveWorkbenchWindow(event).getShell(), "Info",
				"Info for you");
		return null;
	}

}
