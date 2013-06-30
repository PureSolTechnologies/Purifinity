package com.puresol.purifinity.client.common.ui.actions;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractPartSettingsDialog extends Dialog {

    public static final int APPLY_ID = IDialogConstants.CLIENT_ID + 1;
    private static final String APPLY_LABEL = "Apply";

    private final PartSettingsCapability part;

    public AbstractPartSettingsDialog(PartSettingsCapability part) {
	super(part.getSite());
	this.part = part;
	setShellStyle(SWT.TITLE);
    }

    @Override
    protected final void createButtonsForButtonBar(Composite parent) {
	createButton(parent, APPLY_ID, APPLY_LABEL, false);
	createButton(parent, IDialogConstants.CLOSE_ID,
		IDialogConstants.CLOSE_LABEL, true);
    }

    @Override
    protected final void buttonPressed(int buttonId) {
	if (buttonId == APPLY_ID) {
	    if (canApply()) {
		part.applySettings();
	    }
	} else if (buttonId == IDialogConstants.CLOSE_ID) {
	    part.closeSettings();
	    close();
	}
    }

    @Override
    protected final void configureShell(Shell newShell) {
	super.configureShell(newShell);
	newShell.setText("Settings for " + part.getTitle());
    }

    /**
     * The implementing dialog will return True here, if enough settings are
     * made to apply them to the part and if they are consistent. False is
     * returned if the settings are not enough or inconsistent.
     * 
     * @return
     */
    abstract protected boolean canApply();

    /**
     * This method can be called by the view if the settings dialog needs to
     * refresh some settings.
     */
    abstract public void refresh();
}
