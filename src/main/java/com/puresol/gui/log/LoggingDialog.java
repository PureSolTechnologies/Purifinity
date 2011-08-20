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

import javax.i18n4java.Translator;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.puresol.gui.Dialog;
import com.puresol.log.LogAlways;

/**
 * This dialog is for setting the logging level during runtime. More detailed
 * runtime settings for logging might be implemented later.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LoggingDialog extends Dialog {

	private static final long serialVersionUID = 4122721639814570183L;

	private static final Logger logger = Logger.getLogger(LoggingDialog.class);
	private static final Translator translator = Translator
			.getTranslator(LoggingDialog.class);

	private final JComboBox logLevels = new JComboBox();

	public LoggingDialog() {
		super(translator.i18n("Configure Logging"), false);
		initUI();
	}

	private void initUI() {
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(10);
		setLayout(borderLayout);
		add(getDefaultOKButton(), BorderLayout.SOUTH);
		logLevels.addItem(Level.TRACE);
		logLevels.addItem(Level.DEBUG);
		logLevels.addItem(Level.INFO);
		logLevels.addItem(Level.WARN);
		logLevels.addItem(Level.ERROR);
		logLevels.addItem(Level.FATAL);
		logLevels.setSelectedItem(Logger.getRootLogger().getLevel());
		add(logLevels, BorderLayout.CENTER);
		add(new JLabel(translator.i18n("Set log level:")), BorderLayout.NORTH);

		logLevels.addActionListener(this);

		pack();
	}

	private void changeLogLevel(Object o) {
		Level level = (Level) o;
		if (!level.equals(Logger.getRootLogger().getLevel())) {
			logger.log(LogAlways.LOG_ALWAYS, "Changed log level to '" + level
					+ "'!");
			Logger.getRootLogger().setLevel(level);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logLevels) {
			changeLogLevel(logLevels.getSelectedItem());
		} else {
			super.actionPerformed(e);
		}
	}

	public static void main(String[] args) {
		new LoggingDialog().run();
	}
}
