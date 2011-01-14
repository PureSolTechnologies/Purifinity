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

package com.puresol.gui.validator;

import java.util.Date;
import java.util.regex.Pattern;

import com.puresol.gui.data.Time;

public class RegExpValidator extends AbstractValidator {

	private Pattern pattern = null;

	public RegExpValidator(String regExp) {
		pattern = Pattern.compile(regExp);
	}

	public boolean isValid(String input) {
		return pattern.matcher(input).matches();
	}

	public boolean isValid(Boolean input) {
		return pattern.matcher(Boolean.toString(input)).matches();
	}

	public boolean isValid(Float input) {
		return pattern.matcher(Float.toString(input)).matches();
	}

	public boolean isValid(Double input) {
		return pattern.matcher(Double.toString(input)).matches();
	}

	public boolean isValid(Date input) {
		return pattern.matcher(Time.date2String(input)).matches();
	}

	public boolean isValid(Integer input) {
		return pattern.matcher(Integer.toString(input)).matches();
	}

}
