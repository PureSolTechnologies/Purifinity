package com.puresol.gui.osgi;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.i18n4java.Translator;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.puresol.gui.config.PropertiesPanel;
import com.puresol.osgi.BundleConfigurator;

public class BundleConfiguratorPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = -5497942963691954365L;

	private static final Translator translator = Translator
			.getTranslator(BundleConfiguratorPanel.class);

	private final PropertiesPanel propertiesPanel = new PropertiesPanel();
	private final JLabel nameLabel = new JLabel();
	private final JButton okButton = new JButton(translator.i18n("OK"));
	private final JButton applyButton = new JButton(translator.i18n("Apply"));
	private final JButton resetButton = new JButton(translator.i18n("Reset"));
	private final JButton cancelButton = new JButton(translator.i18n("Cancel"));

	public BundleConfiguratorPanel() {
		super();
		initUI();
	}

	public BundleConfiguratorPanel(BundleConfigurator bundleConfigurator) {
		super();
		initUI();
		setBundleConfigurator(bundleConfigurator);
	}

	private void initUI() {
		setLayout(new BorderLayout());

		Font font = nameLabel.getFont();
		nameLabel.setFont(new Font(font.getName(), font.getStyle(), (int) (font
				.getSize() * 1.5)));
		add(nameLabel, BorderLayout.NORTH);

		add(new JScrollPane(propertiesPanel), BorderLayout.CENTER);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		okButton.addActionListener(this);
		buttonsPanel.add(okButton);

		applyButton.addActionListener(this);
		buttonsPanel.add(applyButton);

		resetButton.addActionListener(this);
		buttonsPanel.add(resetButton);

		cancelButton.addActionListener(this);
		buttonsPanel.add(cancelButton);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	public void setBundleConfigurator(BundleConfigurator bundleConfigurator) {
		if (bundleConfigurator == null) {
			nameLabel.setText(translator.i18n("Nothing selected..."));
			propertiesPanel.clearPropertyDescriptions();
		} else {
			nameLabel.setText(bundleConfigurator.getName());
			propertiesPanel.setPropertyDescriptions(bundleConfigurator
					.getSource(), bundleConfigurator.getPropertyDescriptions());
		}
	}

	private void ok() {
		propertiesPanel.apply();
		setBundleConfigurator(null);
	}

	private void apply() {
		propertiesPanel.apply();
	}

	private void reset() {
		propertiesPanel.reset();
	}

	private void cancel() {
		setBundleConfigurator(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			ok();
		} else if (e.getSource() == applyButton) {
			apply();
		} else if (e.getSource() == resetButton) {
			reset();
		} else if (e.getSource() == cancelButton) {
			cancel();
		}
	}
}
