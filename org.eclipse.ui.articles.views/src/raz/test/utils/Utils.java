package raz.test.utils;



import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.articles.application.Activator;

public class Utils {
	
	/**
	 * Returns the image descriptor with the given relative path.
	 */
	public static ImageDescriptor getImageDescriptor(String relativePath) {
		String iconPath = "icons/";
//		try {
//			ViewsPlugin plugin = ViewsPlugin.getDefault();
//			URL installURL = plugin.getDescriptor().getInstallURL();
//			URL url = new URL(installURL, iconPath + relativePath);
//			return ImageDescriptor.createFromURL(url);
			
		return	Activator.getImageDescriptor(iconPath+relativePath);
//		}
//		catch (MalformedURLException e) {
//			// should not happen
//			return ImageDescriptor.getMissingImageDescriptor();
//		}
	}
	
	/**
	 * Read a file path inside an eclipse plugin
	 * */
	public static URL readFile(String path) throws IOException {
		URL fullPathString = FileLocator.find(Platform.getBundle("org.eclipse.ui.articles.views"), new Path(
				path), null);
		return FileLocator.resolve(fullPathString);
		

	}

}
