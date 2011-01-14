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
		SourceHeader.addHeaderToFiles(new File(
				"res/config/SourceFileHeader.template"), new File(
				"res/config/about"), new File("src"), "**/*.java");
		SourceHeader.addHeaderToFiles(new File(
				"res/config/SourceFileHeader.template"), new File(
				"res/config/about"), new File("test"), "**/*.java");
	}
}
