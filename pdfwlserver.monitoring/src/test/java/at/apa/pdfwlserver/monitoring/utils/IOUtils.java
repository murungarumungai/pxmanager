package at.apa.pdfwlserver.monitoring.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
	
	/**
	 * This helper method tries to move the source file and if this doesn't work
	 * (becuase of filesystem boundaries, for example) it tries to copy the file
	 * and delete the old file.
	 * 
	 * @param src
	 * @param dst
	 * @throws IOException
	 */
	public static File safeMoveFile(final File src, File dst) throws IOException {
		if (dst.isDirectory()) {
			dst = new File(dst, src.getName());
		}
		if (!src.renameTo(dst)) {
			if (src.isDirectory()) {
				throw new IOException("recursive copying of directories is not implemented (yet?)");
			}
			copyFile(src, dst);
			if (!src.delete()) {
				if (!dst.delete()) {
					//logger.error("Could not delete file that I just wrote myself: " + dst.getAbsolutePath());
				}
				throw new IOException("Could not delete src file after copying " + src.getAbsolutePath());
			}
		}
		return dst;
	}
	
	
	public static void copyFile(final File source, final File target) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source);

			fos = new FileOutputStream(target);

			IOUtils.copyInputStreamToOutputStream(fis, fos);

			IOUtils.safeClose(fis);
			fis = null;

			IOUtils.safeClose(fos);
			fos = null;
		} finally {
			IOUtils.safeCloseWOException(fis);
			IOUtils.safeCloseWOException(fos);
		}

	}
	
	public static void copyInputStreamToOutputStream(InputStream is, OutputStream os) throws IOException {
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = is.read(buffer)) > 0) {
			os.write(buffer, 0, bytesRead);
		}
	}
	
	/**
	 * Convenience method that tries to close the given closeable if its not
	 * null
	 * 
	 * @param closeable
	 *            The Closeable to close
	 * @throws IOException
	 */
	public static <T extends Closeable> T safeClose(T closeable) throws IOException {
		if (closeable == null) {
			return null;
		}

		closeable.close();

		return null;
	}
	
	/**
	 * Tries to close the given closeable (if its not null) and logs an error in
	 * case of an IOException. This is intended to be used in finally blocks,
	 * where you have to rely on the code to continue
	 * 
	 * @param closeable
	 *            The Closeable to close
	 */
	public static <T extends Closeable> T safeCloseWOException(T closeable) {
		if (closeable == null) {
			return null;
		}

		try {
			closeable.close();
		} catch (Throwable e) {
			// Catching all exceptions including runtimeExceptions here. For
			// being able to use this in finally blocks
			// Intentionally not all throwables
			//logger.error("Exception caught", e);
		}

		return null;
	}

}
