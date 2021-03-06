package com.puresoltechnologies.purifinity.server.common.utils.io;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * This class contains several static methods for easier access to standard
 * functionality.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileTreeConverter {

	public static FileTree convertFileListToTree(String rootName,
			List<File> files) {
		FileTree top = new FileTree(null, rootName);
		for (File file : files) {
			String[] pathComponents = file.getPath().split(
					Pattern.quote(File.separator));
			FileTree current = top;
			for (String component : pathComponents) {
				if (component.isEmpty()) {
					continue;
				}
				FileTree child = current.getChild(component);
				if (child == null) {
					child = new FileTree(current, component);
				}
				current = child;
			}
		}
		return top;
	}
}
