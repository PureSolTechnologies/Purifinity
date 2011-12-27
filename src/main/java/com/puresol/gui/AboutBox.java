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
import java.awt.Container;
import java.awt.FlowLayout;

import javax.i18n4java.Translator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.puresol.config.CustomerInformation;

/**
 * This class implements the standard about box for copyright and license
 * information.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class AboutBox extends PureSolDialog {

    private static final long serialVersionUID = 1L;

    private static final Translator translator = Translator
	    .getTranslator(AboutBox.class);

    /**
     * This is the standard constructor for the about box. Only a parent
     * reference is needed. All other information are standard or taken out of
     * APIConfig and CustomerConfig.
     * 
     * @see com.APIInformation.api.APIConfig
     * @see com.CustomerInformation.config.api.CustomerConfig
     * 
     * @param parent
     *            is the reference to the parent Application showing this
     *            dialog.
     */
    public AboutBox() {
	super(Application.getInstance(), translator.i18n("About"), false);
	initUI();
	initLocation();
    }

    /**
     * This is the method for creating the complete UI.
     */
    private void initUI() {
	Container content = getContentPane();
	JTabbedPane tabbedPane = new JTabbedPane();
	content.add(tabbedPane, BorderLayout.CENTER);
	tabbedPane.add(translator.i18n("Copyright"), new JScrollPane(
		getCopyrightPanel()));
	tabbedPane.add(translator.i18n("Vendor"), new JScrollPane(
		getVendorPanel()));
	tabbedPane.add(translator.i18n("Customer"), new JScrollPane(
		getCustomerPanel()));
	tabbedPane.add(translator.i18n("Contact"), new JScrollPane(
		getContactPanel()));
	setButtonVisible(DialogButtons.OK, true);
    }

    /**
     * This method creates the copyright panel for this about box.
     * 
     * @return The Panel is returned for the TabbedPane.
     */
    private JPanel getCopyrightPanel() {
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	HTMLViewer html = new HTMLViewer();
	html.setText(Application.getCopyrightMessage());
	panel.add(html);
	return panel;
    }

    /**
     * This method creates the vendor panel for this about box.
     * 
     * @return The Panel is returned for the TabbedPane.
     */
    private JPanel getVendorPanel() {
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	HTMLViewer html = new HTMLViewer();
	html.setText(Application.getVendorInformation());
	panel.add(html);
	return panel;
    }

    /**
     * This method creates the customer panel for this about box.
     * 
     * @return The Panel is returned for the TabbedPane.
     */
    private JPanel getCustomerPanel() {
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	HTMLViewer html = new HTMLViewer();
	html.setText(CustomerInformation.getCustomerInformation());
	panel.add(html);
	return panel;
    }

    /**
     * This method creates the contact panel for this about box.
     * 
     * @return The Panel is returned for the TabbedPane.
     */
    private JPanel getContactPanel() {
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout(FlowLayout.LEFT));
	HTMLViewer html = new HTMLViewer();
	html.setText(Application.getContactInformation());
	panel.add(html);
	return panel;
    }

    public static void about() {
	new AboutBox().setVisible(true);
    }
}
