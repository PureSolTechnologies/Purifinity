package com.puresol.gui.coding;

import java.awt.Color;

import javax.swing.JLabel;

import com.puresol.coding.quality.SourceCodeQuality;

public class QualityLabel extends JLabel {

	private static final long serialVersionUID = 8000571179914572829L;

	private SourceCodeQuality quality = SourceCodeQuality.UNSPECIFIED;

	public QualityLabel() {
		super();
	}

	public QualityLabel(SourceCodeQuality quality) {
		super();
		setQuality(quality);
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

	public void setQuality(SourceCodeQuality quality) {
		this.quality = quality;
		setOpaque(true);
		setText(quality.getIdentifier());
		setForeground(Color.BLACK);
		switch (quality) {
		case HIGH:
			setBackground(Color.GREEN);
			break;
		case MEDIUM:
			setBackground(Color.YELLOW);
			break;
		case LOW:
			setBackground(Color.RED);
			break;
		default:
			setBackground(Color.LIGHT_GRAY);
		}
	}

}
