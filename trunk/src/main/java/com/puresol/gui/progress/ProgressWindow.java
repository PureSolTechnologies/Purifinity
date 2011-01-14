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

package com.puresol.gui.progress;

import java.awt.Dimension;

import javax.i18n4java.Translator;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.puresol.gui.Application;

/**
 * This is a dedicated window for showing a progress panel.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProgressWindow extends JFrame implements FinishListener {

	private static final long serialVersionUID = -5428306694138966408L;

	private static final Translator translator = Translator
			.getTranslator(ProgressWindow.class);

	private ProgressPanel progressPanel;
	private final RunnableProgressObservable observable;

	public ProgressWindow(RunnableProgressObservable observable) {
		super(translator.i18n("Progress..."));
		this.observable = observable;
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel();
		setContentPane(panel);
		BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(layout);

		progressPanel = new ProgressPanel(observable);
		panel.add(new JScrollPane(progressPanel));
		progressPanel.addFinishListener(this);

		setMinimumSize(new Dimension(400, 100));
		pack();
	}

	public void pack() {
		super.pack();
		if (Application.getInstance() != null) {
			setLocationRelativeTo(Application.getInstance());
		}
	}

	public void run() {
		setVisible(true);
		progressPanel.run();
	}

	@Override
	public void finished(Runnable runnable) {
		this.dispose();
	}
}
