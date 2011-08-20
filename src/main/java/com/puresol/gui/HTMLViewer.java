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

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;

import org.apache.log4j.Logger;

public class HTMLViewer extends JTextPane implements HyperlinkListener {

	private static final long serialVersionUID = 4941020106098403909L;

	private static final Logger logger = Logger.getLogger(HTMLViewer.class);

	public HTMLViewer() {
		super();
		init();
	}

	private void init() {
		HTMLEditorKit kit = new HTMLEditorKit();
		setEditorKit(kit);
		Document doc = kit.createDefaultDocument();
		setDocument(doc);
		setEditable(false);
		addHyperlinkListener(this);
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent event) {
		if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
			try {
				URL url = event.getURL();
				if (url != null) {
					Desktop.getDesktop().browse(url.toURI());
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				JOptionPane.showConfirmDialog(Application.getInstance(),
						"Could not open link!", "IO Error",
						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			} catch (URISyntaxException e) {
				logger.error(e.getMessage(), e);
				JOptionPane.showConfirmDialog(Application.getInstance(),
						"Could not open link!", "IO Error",
						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
