package com.puresol.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * This is a special tab button for tabbed panes.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TabButton extends JPanel implements ActionListener {

	private static final long serialVersionUID = -1593531170676677125L;

	private final JButton button = new JButton("x");
	private final JTabbedPane pane;

	
	public TabButton(JTabbedPane pane) {
		super();
		this.pane = pane;
		initUI();
	}

	private void initUI() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		setOpaque(false);
		JLabel label = new JLabel() {

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
		button.addActionListener(this);
		add(button);
	}

	private void close() {
		int i = pane.indexOfTabComponent(this);
		if (i != -1) {
			pane.removeTabAt(i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			close();
		}
	}

}
