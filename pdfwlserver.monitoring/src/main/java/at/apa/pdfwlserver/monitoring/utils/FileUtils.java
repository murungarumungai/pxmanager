package at.apa.pdfwlserver.monitoring.utils;

import java.nio.charset.Charset;

public class FileUtils {
	
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

}
