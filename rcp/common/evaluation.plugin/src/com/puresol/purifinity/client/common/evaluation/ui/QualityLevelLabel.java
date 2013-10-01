package com.puresol.purifinity.client.common.evaluation.ui;

import java.math.BigDecimal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresol.commons.math.MathUtils;
import com.puresol.purifinity.client.common.evaluation.metrics.QualityLevelColorProvider;
import com.puresol.purifinity.client.common.ui.SWTUtils;
import com.puresol.purifinity.coding.evaluation.api.QualityLevel;

/**
 * This is a special label to show a {@link QualityLevel} label. The label
 * contains of a {@link Label} telling 'Quality Level' and a special text field
 * containing a text representation of the quality level and a colored
 * background.
 * 
 * @author Rick-Rainer Ludwig
 */
public class QualityLevelLabel extends Composite {

	private static final QualityLevelColorProvider colorProvider = new QualityLevelColorProvider();

	private QualityLevel qualityLevel = null;
	private final Label label;
	private final Text text;
	private Color foregroundColor = new Color(getDisplay(), SWTUtils.BLACK);
	private Color backgroundColor = new Color(getDisplay(), SWTUtils.WHITE);

	public QualityLevelLabel(Composite parent, int style) {
		super(parent, style);
		setLayout(new RowLayout(SWT.HORIZONTAL));
		label = new Label(this, SWT.READ_ONLY);
		label.setText("Quality Level:");
		text = new Text(this, SWT.READ_ONLY);
		text.setBackground(backgroundColor);
		text.setForeground(foregroundColor);
	}

	@Override
	public void dispose() {
		label.dispose();
		text.dispose();
		foregroundColor.dispose();
		backgroundColor.dispose();
		super.dispose();
	}

	public void setQualityLevel(QualityLevel qualityLevel) {
		this.qualityLevel = qualityLevel;
		backgroundColor.dispose();
		foregroundColor.dispose();
		if (qualityLevel != null) {
			text.setText(createText());
			RGB backgroundRGB = colorProvider.getBackgroundColor(qualityLevel);
			if (backgroundRGB != null) {
				backgroundColor = new Color(getDisplay(), backgroundRGB);
			} else {
				backgroundColor = new Color(getDisplay(), SWTUtils.WHITE);
			}
			RGB foregroundRGB = colorProvider.getForegroundColor(qualityLevel);
			if (foregroundRGB != null) {
				foregroundColor = new Color(getDisplay(), foregroundRGB);
			} else {
				foregroundColor = new Color(getDisplay(), SWTUtils.BLACK);
			}
		} else {
			text.setText("");
			backgroundColor = new Color(getDisplay(), SWTUtils.WHITE);
			foregroundColor = new Color(getDisplay(), SWTUtils.BLACK);
		}
		text.setBackground(backgroundColor);
		text.setForeground(foregroundColor);
		text.setSize(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		layout(true, true);
		redraw();
	}

	public QualityLevel getQualityLevel() {
		return qualityLevel;
	}

	private String createText() {
		BigDecimal percentage = MathUtils.round(
				new BigDecimal(qualityLevel.getLevel() * 100.0), 2);
		BigDecimal deviation = MathUtils.round(
				new BigDecimal(qualityLevel.getDeviation() * 100.0), 2);
		return String.format("%2.2f%% +/-%.2f", percentage, deviation);
	}
}
