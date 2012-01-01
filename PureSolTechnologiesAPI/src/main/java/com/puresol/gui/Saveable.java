package com.puresol.gui;

import java.io.File;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;

/**
 * This interface is used for all components which support a storage of their
 * content. This can be used for tabs and other components to save their content
 * without much work around it.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Saveable {

	/**
	 * This method returns an array of file filters which represent different
	 * available formats for output. If null is returned a directory is awaited
	 * for saving.
	 * 
	 * @return
	 */
	public FileFilter[] getPossibleFileFilters();

	/**
	 * This method is called to do the acutal saving. There is no return value.
	 * If there is anything wrong an exception is expected to be thrown.
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void save(File file) throws IOException;

}
