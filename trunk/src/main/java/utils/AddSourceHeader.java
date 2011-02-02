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

package utils;

import java.io.File;

import com.puresol.source.SourceHeader;

/**
 * This small tool adds headers to all source files as specfied in template and
 * about in res/config.
 * 
 * @author Rick-Rainer Ludwig
 */
public class AddSourceHeader {

	public static void main(String[] args) {
		File topDirectory = new File("/home/ludwig/workspace/i18n4java");
		if ((!topDirectory.exists()) || (!topDirectory.isDirectory())) {
			throw new RuntimeException("Directory '" + topDirectory
					+ "' is invalid!");
		}
		File sourceTemplate = new File(topDirectory,
				"src/main/resources/config/SourceFileHeader.template");
		if ((!sourceTemplate.exists()) || (!sourceTemplate.isFile())) {
			throw new RuntimeException("Template file '" + sourceTemplate
					+ "' is invalid!");
		}
		File aboutFile = new File("src/main/resources/config/about");
		if ((!aboutFile.exists()) || (!aboutFile.isFile())) {
			throw new RuntimeException("About file '" + aboutFile
					+ "' is invalid!");
		}

		SourceHeader.addHeaderToFiles(sourceTemplate, aboutFile, new File(
				topDirectory, "src/main/java"), "*.java");
		SourceHeader.addHeaderToFiles(sourceTemplate, aboutFile, new File(
				topDirectory, "src/test/java"), "*.java");
	}
}
