/***************************************************************************
 *
 *   Complexity.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.metrics.cocomo;

import com.puresol.data.Identifiable;

/**
 * This enumeration stands for a complexity. This was derived from the
 * categories of the CoCoMo model. The complexity is defined in three levels:
 * low, medium and high complexity.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public enum SoftwareComplexity implements Identifiable {
    LOW, MEDIUM, HIGH;

    @Override
    public String getIdentifier() {
	return name().toLowerCase();
    }
}
