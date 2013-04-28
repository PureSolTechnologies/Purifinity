package com.puresol.coding.client.common.ui.actions;

/**
 * This interface is used by views which support an export of their content or
 * data.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface Exportable {

    /**
     * This method is implemented to export the content of the implmenting
     * class. This method needs to handle everything which is needed from user
     * specific options to the storage of the data.
     */
    public void export();

}
