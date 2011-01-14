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

import com.puresol.gui.data.Time;

public class DateValidator extends AbstractValidator {

	public boolean isValid(String input) {
		Date date = Time.string2Date(input);
		if (date == null) {
			return false;
		}
		return true;
	}

	public boolean isValid(Boolean input) {
		return false;
	}

	public boolean isValid(Integer input) {
		return false;
	}

	public boolean isValid(Float input) {
		return false;
	}

	public boolean isValid(Double input) {
		return false;
	}

	public boolean isValid(Date input) {
		return true;
	}

}
