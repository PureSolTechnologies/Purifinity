package com.puresol.purifinity.client.common.evaluation.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.puresol.purifinity.client.common.evaluation.metrics.SourceCodeQualityColorProvider;
import com.puresol.purifinity.client.common.ui.SWTColor;
import com.puresol.purifinity.coding.evaluation.api.SourceCodeQuality;

/**
 * This is a special label to show a {@link SourceCodeQuality} label. The label
 * contains of a {@link Label} telling 'Quality' and a special text field
 * containing a text representation of the source code quality and a colored
 * background.
 * 
 * @author Rick-Rainer Ludwig
 */
public class SourceCodeQualityLabel extends Composite {

	private static final SourceCodeQualityColorProvider colorProvider = new SourceCodeQualityColorProvider();

	private SourceCodeQuality sourceCodeQuality = null;
	private final Label label;
	private final Text text;
	private Color foregroundColor = new Color(getDisplay(), SWTColor.BLACK);
	private Color backgroundColor = new Color(getDisplay(), SWTColor.WHITE);

	public SourceCodeQualityLabel(Composite parent, int style) {
		super(parent, style);
		setLayout(new RowLayout(SWT.HORIZONTAL));
		label = new Label(this, SWT.READ_ONLY);
		label.setText("Quality:");
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

	public void setSourceCodeQuality(SourceCodeQuality sourceCodeQuality) {
		this.sourceCodeQuality = sourceCodeQuality;
		backgroundColor.dispose();
		foregroundColor.dispose();
		if (sourceCodeQuality != null) {
			text.setText(sourceCodeQuality.name());
			RGB backgroundRGB = colorProvider
					.getBackgroundColor(sourceCodeQuality);
			if (backgroundRGB != null) {
				backgroundColor = new Color(getDisplay(), backgroundRGB);
			} else {
				backgroundColor = new Color(getDisplay(), SWTColor.WHITE);
			}
			RGB foregroundRGB = colorProvider
					.getForegroundColor(sourceCodeQuality);
			if (foregroundRGB != null) {
				foregroundColor = new Color(getDisplay(), foregroundRGB);
			} else {
				foregroundColor = new Color(getDisplay(), SWTColor.BLACK);
			}
		} else {
			text.setText("");
			backgroundColor = new Color(getDisplay(), SWTColor.WHITE);
			foregroundColor = new Color(getDisplay(), SWTColor.BLACK);
		}
		text.setBackground(backgroundColor);
		text.setForeground(foregroundColor);
		text.setSize(text.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		layout(true, true);
		redraw();
	}

	public SourceCodeQuality getSourceCodeQuality() {
		return sourceCodeQuality;
	}
}
