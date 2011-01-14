/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 * This class provides an enhanced Dialog. This class is optimizied to support
 * all extended features of the API like widgets, signals and slots.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Dialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 261850181983552138L;

	private static final Translator translator = Translator
			.getTranslator(Dialog.class);

	/**
	 * This variable keeps the disposal status of the dialog. True is stored if
	 * the dialog was aborted. If the settings should be kept false is stored.
	 * The default value is false due to the wish that is more safe to reject
	 * changes than keeping unwanted ones.
	 */
	protected boolean confirmed = false;

	private JButton okButton = null;
	private JButton cancelButton = null;
	private JPanel okCancelPanel = null;

	public Dialog() {
		super();
	}

	public Dialog(String title, boolean modal) {
		super(Application.getInstance(), title, modal);
	}

	protected void dialogInit() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				abort();
			}
		});
		super.dialogInit();
	}

	/**
	 * This pack is the enhanced version of JDialogs pack. The enhancement is
	 * that the dialog is automatically centered within the application if there
	 * is one running.
	 */
	public void pack() {
		super.pack();
		if (Application.getInstance() != null) {
			setLocationRelativeTo(Application.getInstance());
		}
	}

	/**
	 * This method is called to start the dialog.
	 * 
	 * @return
	 */
	public boolean run() {
		confirmed = true; /*
						 * if it's a non-modal dialog, confirmed is to be set to
						 * true!
						 */
		setVisible(true);
		return confirmed;
	}

	/**
	 * This slot is called if the dialog is to be aborted and all changes and
	 * setting should be rejected.
	 */
	public void abort() {
		confirmed = false; // this is an abort!
		dispose();
	}

	/**
	 * This slot is called if the settings and changes should be kept and
	 * processed by the parent after closing the dialog.
	 */
	public void quit() {
		confirmed = true;
		dispose();
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void ok() {
		quit();
	}

	public void cancel() {
		abort();
	}

	public void yes() {
		quit();
	}

	public void no() {
		abort();
	}

	/**
	 * This button generates a "OK" Button which is i18ned and already connected
	 * to the quit slot.
	 * 
	 * @return A Button class reference is returned with the generated button to
	 *         be used in customized dialogs.
	 */
	public JButton getDefaultOKButton() {
		if (okButton == null) {
			okButton = new JButton(translator.i18n("OK"));
			okButton.addActionListener(this);
		}
		return okButton;
	}

	/**
	 * This button generates a "Cancel" Button which is i18ned and already
	 * connected to the abort slot.
	 * 
	 * @return A Button class reference is returned with the generated button to
	 *         be used in customized dialogs.
	 */
	public JButton getDefaultCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton(translator.i18n("Cancel"));
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}

	public JPanel createDefaultOkCancelPanel() {
		if (okCancelPanel == null) {
			okCancelPanel = new JPanel();
			okCancelPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			okCancelPanel.add(getDefaultOKButton());
			okCancelPanel.add(getDefaultCancelButton());
		}
		return okCancelPanel;
	}

	/**
	 * This method generates a default layout. This layout is a
	 * BorderLayoutWidget where a OK and a Cancel button is put in the south
	 * direction. These buttons are generated via the button factories. After
	 * calling this method the content pane is a BorderLayoutWidget. This can be
	 * used for to layout the remaining parts of the dialog.
	 * 
	 * @see BorderLayoutWidget
	 * @return A reference to the BorderLayoutWidget is returned which builds
	 *         the top widget of the dialog for later use.
	 */
	public JPanel setDefaultLayout() {
		JPanel borderLayoutWidget = new JPanel();
		setContentPane(borderLayoutWidget);

		borderLayoutWidget.setLayout(new BorderLayout());
		JPanel buttonPanel = new JPanel();
		borderLayoutWidget.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.add(getDefaultOKButton());
		if (isModal()) {
			buttonPanel.add(getDefaultCancelButton());
		}
		return borderLayoutWidget;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			ok();
		} else if (e.getSource() == cancelButton) {
			cancel();
		}
	}
}
