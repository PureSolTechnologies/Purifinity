package com.puresol.coding.client.common.ui.actions;

import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.part.ViewPart;

/**
 * This interface is used with {@link ViewReproductionAction} to create views
 * which can be shown multiple times.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Reproducable {

    /**
     * This method should be implemented by {@link ViewPart}.
     * 
     * @return
     */
    IWorkbenchPartSite getSite();

}
