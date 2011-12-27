package com.puresol.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JRootPane;

/**
 * This is the base class for all corporate designed dialogs.
 * 
 * <b>ATTENTION!!!</b> The content pane is already populated in the south region
 * with the default dialog button panel! The buttons can be made visible and
 * hidden by using setButtonVisible.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PureSolDialog extends JDialog implements DialogButtonsListener {

    private static final long serialVersionUID = 2092429654554738408L;

    private DialogButtonsPanel buttonPanel;

    private DialogButtons closingButton = DialogButtons.CANCEL;

    public PureSolDialog() {
	super();
    }

    public PureSolDialog(Dialog owner, boolean modal) {
	super(owner, modal);
    }

    public PureSolDialog(Dialog owner, String title, boolean modal,
	    GraphicsConfiguration gc) {
	super(owner, title, modal, gc);
    }

    public PureSolDialog(Dialog owner, String title, boolean modal) {
	super(owner, title, modal);
    }

    public PureSolDialog(Dialog owner, String title) {
	super(owner, title);
    }

    public PureSolDialog(Dialog owner) {
	super(owner);
    }

    public PureSolDialog(Frame owner, boolean modal) {
	super(owner, modal);
    }

    public PureSolDialog(Frame owner, String title, boolean modal,
	    GraphicsConfiguration gc) {
	super(owner, title, modal, gc);
    }

    public PureSolDialog(Frame owner, String title, boolean modal) {
	super(owner, title, modal);
    }

    public PureSolDialog(Frame owner, String title) {
	super(owner, title);
    }

    public PureSolDialog(Frame owner) {
	super(owner);
    }

    public PureSolDialog(Window owner, ModalityType modalityType) {
	super(owner, modalityType);
    }

    public PureSolDialog(Window owner, String title, ModalityType modalityType,
	    GraphicsConfiguration gc) {
	super(owner, title, modalityType, gc);
    }

    public PureSolDialog(Window owner, String title, ModalityType modalityType) {
	super(owner, title, modalityType);
    }

    public PureSolDialog(Window owner, String title) {
	super(owner, title);
    }

    public PureSolDialog(Window owner) {
	super(owner);
    }

    @Override
    protected void dialogInit() {
	super.dialogInit();
	initCloseBehavior();
	initUI();
    }

    private void initCloseBehavior() {
	setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		cancel();
	    }
	});
    }

    private void initUI() {
	buttonPanel = new DialogButtonsPanel(this);
	Container contentPane = getContentPane();
	contentPane.setLayout(new BorderLayout());
	contentPane.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * This method is used to set the dialog buttons visible or hidden.
     * 
     * @param button
     * @param visible
     */
    protected void setButtonVisible(DialogButtons button, boolean visible) {
	buttonPanel.setVisible(button, visible);
    }

    protected void setDefaultButton(DialogButtons button) {
	JRootPane rootPane = getRootPane();
	buttonPanel.setDefaultButton(rootPane, button);
    }

    public DialogButtons getClosingButton() {
	return closingButton;
    }

    @Override
    public void yes() {
	closingButton = DialogButtons.YES;
	dispose();
    }

    @Override
    public void no() {
	closingButton = DialogButtons.NO;
	dispose();
    }

    @Override
    public void ok() {
	closingButton = DialogButtons.OK;
	dispose();
    }

    @Override
    public void apply() {
	// Not needed here, but overridden by child classes.
    }

    @Override
    public void reset() {
	// Not needed here, but overridden by child classes.
    }

    @Override
    public void cancel() {
	closingButton = DialogButtons.CANCEL;
	dispose();
    }

    @Override
    public void close() {
	closingButton = DialogButtons.CLOSE;
	dispose();
    }

    @Override
    public void help() {
	// Not needed here, but overridden by child classes.
    }

}
