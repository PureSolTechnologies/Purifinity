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

package com.puresol.gui.log;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.puresol.gui.Dialog;
import com.puresol.log.LogViewerAppender;

/**
 * This class is an interactive logging dialog for Logger. This dialog is flying
 * over the application, so the application is free for use. The dialog shows
 * interactively the logs which newly appear. The log viewer is normally created
 * and view via Logger.showLogViewer
 * 
 * @see com.qsys.api.log.Logger#showLogViewer()
 * 
 * @author Rick-Rainer Ludwig
 */
public class LogViewer extends Dialog {

	static public void startNonModal() {
		new LogViewer().run();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This text area is the place to view the logs.
	 */
	private final JTextArea textArea = new JTextArea();

	/**
	 * This button is for closing the dialog.
	 */
	private final JButton ok = new JButton("ok");

	/**
	 * This is the standard constructor. All initializations are performed here.
	 * 
	 */
	public LogViewer() {
		super();
		this.setTitle("Log Viewer");
		this.setModal(false);
	}

	protected void dialogInit() {
		super.dialogInit();
		JPanel pane = new JPanel();
		pane.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		pane.add(scrollPane, BorderLayout.CENTER);

		pane.add(ok, BorderLayout.SOUTH);
		ok.addActionListener(this);

		LogViewerAppender.getInstance().addViewer(this);

		setContentPane(pane);
	}

	public void addLog(String message) {
		if (message == null) {
			return;
		}
		if (textArea == null) {
			return;
		}
		textArea.append(message);
	}

	public void quit() {
		LogViewerAppender.getInstance().removeViewer(this);
		super.quit();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ok) {
			quit();
		} else {
			super.actionPerformed(e);
		}
	}
}
