package com.puresol.coding.config;

import apps.CodeAnalysis;

import com.puresol.config.sources.HomeFile;
import com.puresol.gui.DialogButtons;
import com.puresol.gui.DialogButtonsListener;
import com.puresol.gui.PureSolDialog;

/**
 * This is the central configuration dialog for CodeAnalysis.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class CodeAnalysisConfigurationDialog extends PureSolDialog implements
	DialogButtonsListener {

    private static final long serialVersionUID = 5702941705895122803L;

    private final HomeFile homeFileSource;

    public CodeAnalysisConfigurationDialog(HomeFile homeFileSource) {
	super(CodeAnalysis.getInstance(), "Code Analysis Configuration", true);
	this.homeFileSource = homeFileSource;
	initUI();
    }

    private void initUI() {
	setButtonVisible(DialogButtons.OK, true);
	setButtonVisible(DialogButtons.CANCEL, true);
	setButtonVisible(DialogButtons.HELP, true);
	pack();
    }

    @Override
    public void ok() {
	super.ok();
    }

    @Override
    public void cancel() {
	super.cancel();
    }

    @Override
    public void help() {
	super.help();
    }
}
