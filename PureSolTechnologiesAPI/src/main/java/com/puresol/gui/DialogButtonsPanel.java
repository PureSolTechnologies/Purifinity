package com.puresol.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRootPane;

/**
 * This class represents the standard buttons for dialogs on the lower edge. The
 * Buttons have a standard sorting and can be switch on and off. The buttons
 * available are in right order: Yes, No, OK, Cancel, Close, Help.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DialogButtonsPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 8797180878887104949L;

    private static final Translator translator = Translator
	    .getTranslator(DialogButtonsPanel.class);

    private final DialogButtonsListener listener;

    private final JButton yesButton = new JButton(translator.i18n("Yes"));
    private final JButton noButton = new JButton(translator.i18n("No"));
    private final JButton okButton = new JButton(translator.i18n("OK"));
    private final JButton cancelButton = new JButton(translator.i18n("Cancel"));
    private final JButton closeButton = new JButton(translator.i18n("Close"));
    private final JButton helpButton = new JButton(translator.i18n("Help"));

    public DialogButtonsPanel(DialogButtonsListener listener) {
	super();
	this.listener = listener;
	initUI();
    }

    private void initUI() {
	setLayout(new FlowLayout(FlowLayout.TRAILING));

	yesButton.setVisible(false);
	noButton.setVisible(false);
	okButton.setVisible(false);
	cancelButton.setVisible(false);
	closeButton.setVisible(false);
	helpButton.setVisible(false);

	add(yesButton);
	add(noButton);
	add(okButton);
	add(cancelButton);
	add(closeButton);
	add(helpButton);

	yesButton.addActionListener(this);
	noButton.addActionListener(this);
	okButton.addActionListener(this);
	cancelButton.addActionListener(this);
	closeButton.addActionListener(this);
	helpButton.addActionListener(this);
    }

    public void setVisible(DialogButtons button, boolean visible) {
	switch (button) {
	case YES:
	    yesButton.setVisible(visible);
	    break;
	case NO:
	    noButton.setVisible(visible);
	    break;
	case OK:
	    okButton.setVisible(visible);
	    break;
	case CANCEL:
	    cancelButton.setVisible(visible);
	    break;
	case CLOSE:
	    closeButton.setVisible(visible);
	    break;
	case HELP:
	    helpButton.setVisible(visible);
	    break;
	default:
	    throw new IllegalArgumentException("Button '" + button.name()
		    + "' is unknown!");
	}
    }

    public void setDefaultButton(JRootPane rootPane, DialogButtons button) {
	switch (button) {
	case YES:
	    rootPane.setDefaultButton(yesButton);
	    break;
	case NO:
	    rootPane.setDefaultButton(noButton);
	    break;
	case OK:
	    rootPane.setDefaultButton(okButton);
	    break;
	case CANCEL:
	    rootPane.setDefaultButton(cancelButton);
	    break;
	case CLOSE:
	    rootPane.setDefaultButton(closeButton);
	    break;
	case HELP:
	    rootPane.setDefaultButton(helpButton);
	    break;
	default:
	    throw new IllegalArgumentException("Button '" + button.name()
		    + "' is unknown!");
	}
    }

    @Override
    public void actionPerformed(ActionEvent event) {
	if (event.getSource() == yesButton) {
	    listener.yes();
	} else if (event.getSource() == noButton) {
	    listener.no();
	} else if (event.getSource() == okButton) {
	    listener.ok();
	} else if (event.getSource() == cancelButton) {
	    listener.cancel();
	} else if (event.getSource() == closeButton) {
	    listener.close();
	} else if (event.getSource() == helpButton) {
	    listener.help();
	} else {
	    throw new IllegalStateException(
		    "Could not handle event from component '"
			    + event.getSource().getClass() + "'!");
	}
    }

}
