package raz.memo.reader.popup.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import raz.memo.reader.DateUtil;
import raz.memo.reader.MemoNote;

public class MemoAction implements IObjectActionDelegate {

	static final String VNT_EXTENSION = ".vnt";
	static final String OUTPUT_EXTENSION = ".txt";

	static final String OUTPUT_FOLDER = "Output";

	private Shell shell;

	private Object[] selectedItems;

	private int inputItems = 0;
	private int outputItems = 0;

	private File outputFolder;

	/**
	 * Constructor for Action1.
	 */
	public MemoAction() {
		super();
	}

	/**
	 * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
	 */
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		shell = targetPart.getSite().getShell();
	}

	/**
	 * @see IActionDelegate#run(IAction)
	 */
	public void run(IAction action) {
		// every run, reset the counters
		//every run reset the output folder
		outputFolder = null;
		inputItems = 0;
		outputItems = 0;
		if (null != selectedItems) {

			for (Object oneSelection : selectedItems) {
				try {
					processSelection(oneSelection);

				} catch (Exception e) {
					e.printStackTrace();
					MessageDialog.openError(shell, "Error procesing:"
							+ oneSelection.toString(), e.getMessage());
				}
			}
			if (inputItems > 0) {
				MessageDialog.openInformation(shell, "Reader", "Input items:"
						+ inputItems + "Output items:" + outputItems
						+ " . Memo Files were read and processed in path:"
						+ outputFolder.getPath());
			}
		}
	}

	/**
	 * @see IActionDelegate#selectionChanged(IAction, ISelection)
	 */
	public void selectionChanged(IAction action, ISelection selection) {
		// System.out.println("Selection Changed");
		StructuredSelection ss = (StructuredSelection) selection;
		if (ss.isEmpty())
			return;

		Object[] selectedObjects = ss.toArray();

		if (null != selectedObjects && selectedObjects.length > 0) {
			selectedItems = selectedObjects;
		}
	}

	private void processSelection(Object selection) throws CoreException,
			IOException {

		String filePath = selection.toString();

		if (filePath.endsWith(VNT_EXTENSION)) {
			inputItems++;
			// chances are it will be a .vnt file, go ahead and process it
			// Object o = ss.getFirstElement();
			org.eclipse.core.internal.resources.File eclipseFile = (org.eclipse.core.internal.resources.File) selection;

			InputStream is = eclipseFile.getContents();
			String content = readInputStreamAsString(is);
			// TODO: here just after I read the 1st file it means that I have
			// access to a phisical
			// location/folder on disk => initialize the folder of the
			// firstFile, as the output folder
			if (outputFolder == null) {
				initOutputDir(eclipseFile.getLocation().toFile());
			}

			System.out.println("Reading selected file:" + content);

			MemoNote note = new MemoNote(content);

			String processedNoteContent = note.getContent();
			Date lastModified = note.getLastModified();

			System.out.println("Output:" + processedNoteContent);

			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1,
					filePath.indexOf(VNT_EXTENSION));

			if (lastModified != null) {
				fileName = fileName.concat("_");
				fileName = fileName.concat(DateUtil.normalFormatter
						.format(lastModified));
			}
			fileName = fileName.concat(OUTPUT_EXTENSION);
			File output = new File(outputFolder, fileName);

			writeStringToFile(output.getPath(), processedNoteContent);
			outputItems++;
		} else {
			MessageDialog.openInformation(shell, "Wrong File Extension",
					"Wrong File Extension, file name doesnt have:"
							+ VNT_EXTENSION + " extension");
		}

	}

	/**
	 * parses the filePath and extracts the directory where this file is located
	 * */
	private void initOutputDir(File filePath) {
		String currentDir = new java.io.File(".").getAbsolutePath();
		System.out.println(currentDir);

		// File file = new File(filePath);

		if (filePath.isFile()) {
			outputFolder = new File(filePath.getParentFile(), OUTPUT_FOLDER);
			if (outputFolder.exists() && outputFolder.isDirectory()) {
				return;// Output folder already exists
			}
			boolean result = outputFolder.mkdir();
			if (!result) {
				throw new IllegalStateException(
						"I was not able to create the Output dir, I can't continue. Check the filesytem at path:"
								+ outputFolder.getAbsolutePath());
			}
		}
	}

	public static void writeStringToFile(String fileName, String content)
			throws FileNotFoundException {
		PrintWriter out = null;
		try {
			out = new PrintWriter(fileName);

			out.print(content);

			// out.close();
		} finally {
			if (null != out) {
				out.close();
			}
		}
	}

	public static String readInputStreamAsString(InputStream is)
			throws IOException {
		// String result = null;
		//
		// To convert the InputStream to String we use the
		// Reader.read(char[] buffer) method. We iterate until the
		// Reader return -1 which means there's no more data to
		// read. We use the StringWriter class to produce the string.
		//
		if (is != null) {
			Writer writer = new StringWriter();

			char[] buffer = new char[1024];
			try {
				Reader reader = new BufferedReader(new InputStreamReader(is,
						"UTF-8"));
				int n;
				while ((n = reader.read(buffer)) != -1) {
					writer.write(buffer, 0, n);
				}
			} finally {
				is.close();
			}
			return writer.toString();
		} else {
			return "";
		}

	}

}
