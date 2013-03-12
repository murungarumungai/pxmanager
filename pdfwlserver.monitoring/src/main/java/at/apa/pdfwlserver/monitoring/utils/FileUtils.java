package at.apa.pdfwlserver.monitoring.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Pattern;

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
			throw new IllegalArgumentException("File path:" + dirPath
					+ " is not a Directory or does not exist!");
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
	 * <p>
	 * should return the date when the file was created/copied on this file
	 * system. That is: the date when the file was received on this fileSystem
	 * </p>
	 * */
	public static Date getReceivedDate(File filePath) {
		Date dateReceived = new Date(filePath.lastModified());
		return dateReceived;
	}

	/**
	 * return the latest file from this directory which has the specified
	 * extension may return null if there are no files in dir
	 * */
	public static File getLatestFileFromDir(File dirPath, final String fileExt) {
		File file = null;
		File[] files = dirPath.listFiles(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				return name.toLowerCase().endsWith(fileExt);
			}
		});
		
		Arrays.sort(files, new Comparator<File>() {

			public int compare(File o1, File o2) {
				// sort by timestamp, older first
                Long l1 = o1.lastModified();
                Long l2 = o2.lastModified();
                return l1.compareTo(l2);
			}
		});
		
		if(files.length>0){
			file = files[files.length-1]; //return latest file
		}
		
		return file;
		
	}
	
	public static File getLatestFileFromDir(File dirPath, Date earliestDataDelivery, Date nextEarliestDataDelivery, final String fileExt) {
		File file = null;
		file = getLatestFileFromDir(dirPath, fileExt);
		
		if(null!=file){
			Date modifDate = getReceivedDate(file);
			if( (modifDate.equals(earliestDataDelivery) || modifDate.after(earliestDataDelivery)) && modifDate.before(nextEarliestDataDelivery) ){
				//ok! file is withinCheckInterval
			} else{
				//not ok! file is older than checkInterval
				file = null;
			}
		}
		return file;
	}

	// TODO Razvan also see PDF-Importer-Core. IncomingDirectoryImpl
	/*
	 * public File peekNextIncomingFile() { final File[] files =
	 * directory.listFiles(new FileFilter() {
	 * 
	 * @Override public boolean accept(final File file) { final boolean
	 * nameMatches = fileMatches(file); if (!nameMatches) { return false; }
	 * 
	 * if (minIncomingFileAge > 0) { final long age = System.currentTimeMillis()
	 * - file.lastModified(); if (age < minIncomingFileAge) {
	 * logger.debug("Ignoring file " + file.getAbsolutePath() +
	 * " because it is only " + age + "ms old. Minimum age is: " +
	 * minIncomingFileAge); return false; } }
	 * 
	 * return true; } });
	 * 
	 * Arrays.sort(files, new Comparator<File>() {
	 * 
	 * @Override public int compare(final File o1, final File o2) { // sort by
	 * timestamp, older first Long l1 = o1.lastModified(); Long l2 =
	 * o2.lastModified(); return l1.compareTo(l2); } });
	 * 
	 * if (files.length > 0) { logger.debug("Found " + files.length +
	 * " matching files in directory " + directory.getAbsolutePath() +
	 * " - Returning " + files[0].getAbsolutePath()); return files[0]; }
	 * 
	 * logger.debug("Didn't find a matching file in directory: " +
	 * directory.getAbsolutePath()); return null; }
	 */

}
