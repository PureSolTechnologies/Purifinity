package com.puresol.gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swingx.Button;
import javax.swingx.Label;
import javax.swingx.Panel;
import javax.swingx.TabbedPane;
import javax.swingx.connect.Slot;

/**
 * This is a special tab button for tabbed panes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TabButton extends Panel {

	private static final long serialVersionUID = -1593531170676677125L;

	private final TabbedPane pane;

	public TabButton(TabbedPane pane) {
		super();
		this.pane = pane;
		initUI();
	}

	private void initUI() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setOpaque(false);
		Label label = new Label() {

			private static final long serialVersionUID = -6980905788605760509L;

			public String getText() {
				int i = pane.indexOfTabComponent(TabButton.this);
				if (i != -1) {
					return pane.getTitleAt(i);
				}
				return null;
			}
		};
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		add(label);
		Button button = new Button("x");
		button.connect("start", this, "close");
		add(button);
	}

	@Slot
	public void close() {
		int i = pane.indexOfTabComponent(this);
		if (i != -1) {
			pane.removeTabAt(i);
		}
	}
}
