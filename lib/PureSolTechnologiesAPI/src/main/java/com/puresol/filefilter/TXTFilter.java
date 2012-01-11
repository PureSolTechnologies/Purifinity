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

import java.io.Serializable;

import javax.i18n4java.Translator;

public class TXTFilter extends AbstractFileFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Translator translator = Translator
			.getTranslator(BMPFilter.class);

	@Override
	public String getDescription() {
		return translator.i18n("ASCII text files (*.txt)");
	}

	public String getSuffixes() {
		return ".txt";
	}
}