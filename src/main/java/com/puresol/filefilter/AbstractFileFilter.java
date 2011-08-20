/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.filefilter;

import java.io.File;
import java.io.Serializable;

import javax.swing.filechooser.FileFilter;

/**
 * This is an abstract implementation of an enhance file filter for
 * application's file open dialogs. Additionally, the check for correct
 * suffixes, the appendation of suffixes and the search for primary suffix are
 * integrated which were not implemented in FileFilter.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
abstract public class AbstractFileFilter extends FileFilter implements
		Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}
		return hasCorrectSuffix(f);
	}

	public boolean hasCorrectSuffix(File f) {
		String[] extensions = getSuffixes().split(",");
		for (int index = 0; index < extensions.length; index++) {
			if (f.getName().toLowerCase().endsWith(extensions[index])) {
				return true;
			}
		}
		return false;
	}

	public String getFirstSuffix() {
		return getSuffixes().split(",")[0];
	}

	public File appendSuffix(File file) {
		if (hasCorrectSuffix(file)) {
			return file;
		}
		return new File(file.getPath() + getFirstSuffix());
	}

	abstract public String getSuffixes();

	@Override
	abstract public String getDescription();
}
