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
import java.io.IOException;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * PasswordDialog is an standard object to present a password dialog. It is used
 * for all authentication processes with databases and within the user
 * administration.
 * 
 * @author Rick-Rainer Ludwig
 */
public final class LoginDialog extends PureSolDialog {

    private static final long serialVersionUID = 1L;

    private static final Translator translator = Translator
	    .getTranslator(LoginDialog.class);

    private final JLabel message = new JLabel();
    /**
     * This JTextField holds the user name.
     */
    private final JTextField username = new JTextField();

    /**
     * This JPasswordField holds the password. It is not shown and only
     * represented as stars.
     */
    private final JPasswordField password = new JPasswordField();

    /**
     * This is the constructor for PasswordDialog.
     * 
     * @param parent
     * 
     * @param owner
     *            is the calling parent window. If the password dialog is to be
     *            used during startup without a parent window, that null should
     *            be used.
     */
    public LoginDialog() {
	super(Application.getInstance(), translator.i18n("User Confirmation"),
		true);
	initUI();
	initLocation();
    }

    private void initUI() {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	message.setVisible(false);
	panel.add(message);
	panel.add(new JLabel(translator.i18n("Username")));
	panel.add(username);
	panel.add(new JLabel(translator.i18n("Password")));
	panel.add(password);

	setButtonVisible(DialogButtons.OK, true);
	setButtonVisible(DialogButtons.CANCEL, true);

	getContentPane().add(panel, BorderLayout.CENTER);

	setDefaultButton(DialogButtons.OK);
    }

    /**
     * This method returns the set user name after the dialog was closed.
     * 
     * @return A string with the user name is returned.
     * @throws IOException
     *             is thrown in a case of IO error.
     */
    public String getUsername() {
	if (getClosingButton() == DialogButtons.OK) {
	    return "";
	}
	return username.getText();
    }

    /**
     * This method returns the set password after the dialog was closed.
     * 
     * @return A string with the password is returned.
     * @throws IOException
     *             is thrown in a case of IO error.
     */
    public String getPassword() {
	if (getClosingButton() == DialogButtons.OK) {
	    return "";
	}
	return String.valueOf(password.getPassword());
    }

    public void setMessage(String message) {
	this.message.setVisible(true);
	this.message.setText(message);
    }

    public void setUsername(String name) {
	username.setText(name);
    }
}
