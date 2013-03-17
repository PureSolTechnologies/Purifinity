package com.puresol.coding.client.common.ui.actions;

/**
 * This interface is implemented by parts which want to use the
 * {@link RefreshAction} action.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Refreshable {

    /**
     * This method is called by {@link RefreshAction} when the action is
     * invoked.
     */
    public void refresh();

}
