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
import java.awt.Container;

import javax.i18n4java.Translator;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.Level;

import com.puresol.gui.Application;
import com.puresol.gui.DialogButtons;
import com.puresol.gui.PureSolDialog;
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
public class LogViewer extends PureSolDialog {

    private static final long serialVersionUID = 1L;

    private static final Translator translator = Translator
	    .getTranslator(LogViewer.class);

    public static void startNonModal() {
	new LogViewer().setVisible(true);
    }

    /**
     * This text area is the place to view the logs.
     */
    private JTextArea textArea;

    /**
     * This is the standard constructor. All initializations are performed here.
     * 
     */
    public LogViewer() {
	super(Application.getInstance(), translator.i18n("Log Viewer"), false);
	initUI();
	initLocation();
    }

    private void initUI() {
	Container pane = getContentPane();

	textArea = new JTextArea();
	textArea.setEditable(false);
	JScrollPane scrollPane = new JScrollPane(textArea);
	scrollPane
		.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	scrollPane
		.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	pane.add(scrollPane, BorderLayout.CENTER);

	setButtonVisible(DialogButtons.CLOSE, true);

	LogViewerAppender.getInstance().addViewer(this);
    }

    public void addLog(Level level, String message) {
	if (message == null) {
	    return;
	}
	if (textArea == null) {
	    return;
	}
	textArea.append(message);
	textArea.append("\n");
    }

    @Override
    public void cancel() {
	LogViewerAppender.getInstance().removeViewer(this);
	super.cancel();
    }

    @Override
    public void close() {
	LogViewerAppender.getInstance().removeViewer(this);
	super.close();
    }
}
