package com.puresol.gui.osgi;

import java.awt.BorderLayout;

import javax.i18n4java.Translator;
import javax.swingx.Dialog;
import javax.swingx.Panel;
import javax.swingx.Tree;

public class BundleConfigurationDialog extends Dialog {

	private static final long serialVersionUID = -359245614681727468L;

	private static final Translator translator = Translator
			.getTranslator(BundleConfigurationDialog.class);

	private final Tree configuratorTreeView = new Tree();

	public BundleConfigurationDialog() {
		super(translator.i18n("Plugin Configurations"), true);
		initUI();
	}

	private void initUI() {
		Panel panel = new Panel();
		setContentPane(panel);
		panel.setLayout(new BorderLayout());
		panel.add(configuratorTreeView, BorderLayout.WEST);
		panel.add(new Panel(), BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		new BundleConfigurationDialog().run();
	}
}
