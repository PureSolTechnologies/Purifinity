package com.puresol.gui;

/**
 * This interface is implemented by dialogs using the DialogDefaultButtonPanel.
 * The methods implemented for this interface are called as the buttons are
 * pressed.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface DialogButtonsListener {

    /**
     * This method is invoked by pressing the "Yes" button.
     */
    public void yes();

    /**
     * This method is invoked by pressing the "No" button.
     */
    public void no();

    /**
     * This method is invoked by pressing the "OK" button.
     */
    public void ok();

    /**
     * This method is invoked by pressing the "Apply" button.
     */
    public void apply();

    /**
     * This method is invoked by pressing the "Reset" button.
     */
    public void reset();

    /**
     * This method is invoked by pressing the "Cancel" button.
     */
    public void cancel();

    /**
     * This method is invoked by pressing the "Close" button.
     */
    public void close();

    /**
     * This method is invoked by pressing the "Help" button.
     */
    public void help();

}
