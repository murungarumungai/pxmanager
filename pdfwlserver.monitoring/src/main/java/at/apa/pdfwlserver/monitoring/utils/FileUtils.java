package at.apa.pdfwlserver.monitoring.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

public class FileUtils {

	/**
	 * The system-dependent file-separator character. <br>
	 * ("\" on Windows, "/" on UNIX)
	 * 
	 * @see System#getProperties()
	 */
	@SuppressWarnings("nls")
	public static final String FILE_SEPARATOR = System.getProperty(
			"file.separator", "/");

	/**
	 * Returns the default <code>Charset</code> of the Java virtual machine.
	 * <p>
	 * The default <code>Charset</code> is determined during virtual-machine
	 * startup, and typically depends upon the locale and charset of the
	 * underlying operating system.
	 * 
	 * @return The default <code>Charset</code>.
	 * 
	 * @see Charset#defaultCharset()
	 */
	public static Charset getDefaultCharset() {
		return Charset.defaultCharset();
	}

	/**
	 * Checks that the dir exists returns the canonicalFilePath of the dir
	 * 
	 * @return Returns the canonical form of this abstract pathname
	 * @throws IOException
	 *             If an I/O error occurs, which is possible because the
	 *             construction of the canonical pathname may require filesystem
	 *             queries
	 * 
	 * */
	public static File checkDirExists(String dirPath) throws IOException {
		File file = null;
		file = new File(dirPath);
		if (file.exists() && file.isDirectory()) {
			file = file.getCanonicalFile();
		}
		return file;
	}

	/**
	 * <p>
	 * Check if the dir exists returns the canonicalFilePath of the dir
	 * 
	 * @throws IOException
	 *             If an I/O error occurs
	 *             </p>
	 * */
	public static File checkDirExists(File dirPath) throws IOException {

		if (dirPath.exists() && dirPath.isDirectory()) {
			return dirPath.getCanonicalFile();
		} else {
			throw new IllegalArgumentException("File path:"+dirPath+ " is not a Directory or does not exist!");
		}

	}

	/**
	 * Check that the file exists, is file, and can be read
	 * 
	 * @throws IOException
	 * */
	public static File checkFileExists(String filePath) throws IOException {
		File file = null;
		file = new File(filePath);
		if (file.exists() && file.isFile() && file.canRead()) {
			file = file.getCanonicalFile();
		}
		return file;
	}

	/**
	 * gets the last value(last folder) after <code>/ FileSeparator</code>
	 * */
	public static String getLastFolderInPath(File canonicalDirPath) {
		String result = null;
		String path = canonicalDirPath.toString();
		int lastIndex = path.lastIndexOf(FILE_SEPARATOR);

		if (lastIndex < path.length() - 1) {
			// if not on the last position there is something to extract
			result = path.substring(lastIndex + 1);
		}

		return result;
	}
	
	/**
	 * should return the date when the file was created/copied on this file system
	 * the date when the file was received on this fileSystem
	 * */
	public static Date getReceivedDate(File filePath){
		Date dateReceived = new Date(filePath.lastModified());
		return dateReceived;
	}
	
	public static File getLatestFileFromDir(File dirPath){
		File file = null;
		return file;
	}

}
