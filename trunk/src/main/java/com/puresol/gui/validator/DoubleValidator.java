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

public class DoubleValidator extends AbstractValidator {

	private double upperLimit = 0;
	private boolean upperLimitSet = false;
	private double lowerLimit = 0;
	private boolean lowerLimitSet = false;

	public void setLimits(int lowerLimit, int upperLimit) {
		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
		lowerLimitSet = true;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
		upperLimitSet = true;
	}

	public boolean isValid(String input) {
		try {
			Double doubleValue = Double.valueOf(input);
			return isValid(doubleValue);
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public boolean isValid(Boolean input) {
		return false;
	}

	public boolean isValid(Float input) {
		return false;
	}

	public boolean isValid(Double input) {
		if (upperLimitSet) {
			if (input > upperLimit) {
				return false;
			}
		}
		if (lowerLimitSet) {
			if (input < lowerLimit) {
				return false;
			}
		}
		return true;
	}

	public boolean isValid(Date input) {
		return false;
	}

	public boolean isValid(Integer input) {
		return isValid((double) input);
	}

}
