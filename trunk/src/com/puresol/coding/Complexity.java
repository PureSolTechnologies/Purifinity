/***************************************************************************
 *
 *   Complexity.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding;

import com.puresol.exceptions.StrangeSituationException;

public enum Complexity {
	LOW, MEDIUM, HIGH;

	public String toString() {
		if (this == LOW) {
			return "low";
		} else if (this == MEDIUM) {
			return "medium";
		} else if (this == HIGH) {
			return "high";
		}
		throw new StrangeSituationException(
				"Here is a text for a complexity missing!");
	}
}
