package com.puresol.coding.client.views;

import org.eclipse.jface.viewers.ISelection;

/**
 * This class represents a global file analysis selection. This selection is
 * processed by every GUI element which is related to this global selection.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FileAnalysisSelection implements ISelection {

    @Override
    public boolean isEmpty() {
	return true;
    }

}
