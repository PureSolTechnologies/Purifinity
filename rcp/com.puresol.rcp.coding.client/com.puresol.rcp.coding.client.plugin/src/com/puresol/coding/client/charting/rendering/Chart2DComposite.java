package com.puresol.coding.client.charting.rendering;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import swing2swt.layout.BorderLayout;

import com.puresol.coding.client.charting.Chart2D;
import com.puresol.coding.client.charting.Legend;
import com.puresol.coding.client.charting.Title;
import com.puresol.coding.client.controls.VerticalLabel;

/**
 * This class provides a chart canvas for charts with all interactive features
 * available.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Chart2DComposite extends Composite {

    private final Color CHART_BACKGROUND_COLOR = new Color(getDisplay(),
	    new RGB(255, 255, 255));

    private final FontData TITLE_FONT_DATA = new FontData("Arial", 20, SWT.BOLD);
    private final Font TITLE_FONT = new Font(getDisplay(), TITLE_FONT_DATA);

    private final FontData SUB_TITLE_FONT_DATA = new FontData("Arial", 16,
	    SWT.BOLD);
    private final Font SUB_TITLE_FONT = new Font(getDisplay(),
	    SUB_TITLE_FONT_DATA);

    private final FontData CAPTION_FONT_DATA = new FontData("Arial", 12,
	    SWT.ITALIC);
    private final Font CAPTION_FONT = new Font(getDisplay(), CAPTION_FONT_DATA);

    private final FontData TEXT_FONT_DATA = new FontData("Arial", 10, SWT.NONE);
    private final Font TEXT_FONT = new Font(getDisplay(), TEXT_FONT_DATA);

    private Chart2D chart2D;

    private Composite titleComposite;
    private Composite northTitlesArea;
    private Composite southTitlesArea;

    private Composite titlesArea;
    private Composite subTitlesArea;
    private Composite captionArea;
    private Composite textArea;

    private Composite legendComposite;
    private Composite northLegendsArea;
    private Composite eastLegendsArea;
    private Composite southLegendsArea;
    private Composite westLegendsArea;

    public Chart2DCanvas chart;

    public Chart2DComposite(Composite parent, int style) {
	super(parent, style);
	initGUI();
    }

    public Chart2DComposite(Composite parent, int style, Chart2D chart2D) {
	super(parent, style);
	this.chart2D = chart2D;
	initGUI();
    }

    @Override
    public void dispose() {
	TITLE_FONT.dispose();
	CHART_BACKGROUND_COLOR.dispose();
	super.dispose();
    }

    private void initGUI() {
	setLayout(new BorderLayout());

	addTitleComposite();
	addLegendComposite();
	addGraphCanvas();

	addTitles();
	addLegends();

	this.pack();
    }

    private void addTitleComposite() {
	if (titleComposite == null) {
	    titleComposite = new Composite(this, SWT.NONE);
	    titleComposite.setLayoutData(BorderLayout.CENTER);
	    titleComposite.setLayout(new BorderLayout());
	}

	if (northTitlesArea == null) {
	    northTitlesArea = new Composite(titleComposite, SWT.NONE);
	    northTitlesArea.setLayoutData(BorderLayout.NORTH);
	    northTitlesArea.setLayout(new FillLayout(SWT.VERTICAL));
	}

	if (southTitlesArea == null) {
	    southTitlesArea = new Composite(titleComposite, SWT.NONE);
	    southTitlesArea.setLayoutData(BorderLayout.SOUTH);
	    southTitlesArea.setLayout(new FillLayout(SWT.VERTICAL));
	}

	if (titlesArea != null) {
	    titlesArea.dispose();
	}
	titlesArea = new Composite(northTitlesArea, SWT.NONE);
	titlesArea.setLayout(new FillLayout(SWT.VERTICAL));

	if (subTitlesArea != null) {
	    subTitlesArea.dispose();
	}
	subTitlesArea = new Composite(northTitlesArea, SWT.NONE);
	subTitlesArea.setLayout(new FillLayout(SWT.VERTICAL));

	if (captionArea != null) {
	    captionArea.dispose();
	}
	captionArea = new Composite(southTitlesArea, SWT.NONE);
	captionArea.setLayout(new FillLayout(SWT.VERTICAL));

	if (textArea != null) {
	    textArea.dispose();
	}
	textArea = new Composite(southTitlesArea, SWT.NONE);
	textArea.setLayout(new FillLayout(SWT.VERTICAL));
    }

    private void addLegendComposite() {
	if (legendComposite == null) {
	    legendComposite = new Composite(titleComposite, SWT.NONE);
	    legendComposite.setLayoutData(BorderLayout.CENTER);
	    legendComposite.setLayout(new BorderLayout());
	}

	if (northLegendsArea != null)
	    northLegendsArea.dispose();
	northLegendsArea = new Composite(legendComposite, SWT.NONE);
	northLegendsArea.setLayoutData(BorderLayout.NORTH);
	northLegendsArea.setLayout(new FillLayout(SWT.VERTICAL));

	if (eastLegendsArea != null)
	    eastLegendsArea.dispose();
	eastLegendsArea = new Composite(legendComposite, SWT.NONE);
	eastLegendsArea.setLayoutData(BorderLayout.EAST);
	eastLegendsArea.setLayout(new FillLayout(SWT.HORIZONTAL));

	if (southLegendsArea != null)
	    southLegendsArea.dispose();
	southLegendsArea = new Composite(legendComposite, SWT.NONE);
	southLegendsArea.setLayoutData(BorderLayout.SOUTH);
	southLegendsArea.setLayout(new FillLayout(SWT.VERTICAL));

	if (westLegendsArea != null)
	    westLegendsArea.dispose();
	westLegendsArea = new Composite(legendComposite, SWT.NONE);
	westLegendsArea.setLayoutData(BorderLayout.WEST);
	westLegendsArea.setLayout(new FillLayout(SWT.HORIZONTAL));
    }

    private void addGraphCanvas() {
	chart = new Chart2DCanvas(legendComposite, SWT.BORDER);
	chart.setBackground(CHART_BACKGROUND_COLOR);
	chart.setLayoutData(BorderLayout.CENTER);
    }

    private void addTitles() {
	if (chart2D == null)
	    return;
	List<Title> titles = chart2D.getTitles();
	if (titles == null)
	    return;
	for (Title title : titles) {
	    switch (title.getType()) {
	    case TITLE:
		Label titleLabel = new Label(titlesArea, SWT.CENTER);
		titleLabel.setFont(TITLE_FONT);
		titleLabel.setText(title.getText());
		break;
	    case SUB_TITLE:
		Label subTitleLabel = new Label(subTitlesArea, SWT.CENTER);
		subTitleLabel.setFont(SUB_TITLE_FONT);
		subTitleLabel.setText(title.getText());
		break;
	    case CAPTION:
		Label captionLabel = new Label(captionArea, SWT.CENTER);
		captionLabel.setFont(CAPTION_FONT);
		captionLabel.setText(title.getText());
		break;
	    case TEXT:
		Label textLabel = new Label(textArea, SWT.CENTER);
		textLabel.setFont(TEXT_FONT);
		textLabel.setText(title.getText());
		break;
	    }
	}
    }

    private void addLegends() {
	if (chart2D == null)
	    return;
	List<Legend> legends = chart2D.getLegends();
	if (legends == null)
	    return;
	for (Legend legend : legends) {
	    switch (legend.getPosition()) {
	    case NORTH:
		Label northLabel = new Label(northLegendsArea, SWT.CENTER);
		northLabel.setText("North Legend");
		break;
	    case EAST:
		VerticalLabel eastLabel = new VerticalLabel(eastLegendsArea,
			SWT.NONE);
		eastLabel.setText("East Legend");
		break;
	    case SOUTH:
		Label southLabel = new Label(southLegendsArea, SWT.CENTER);
		southLabel.setText("South Legend");
		break;
	    case WEST:
		VerticalLabel westLabel = new VerticalLabel(westLegendsArea,
			SWT.NONE);
		westLabel.setText("West Legend");
		break;
	    }
	}
    }

    public Chart2D getChart2D() {
	return chart2D;
    }

    public void setChart2D(Chart2D chart2d) {
	chart2D = chart2d;
	updateChart();
    }

    private void updateChart() {
	chart.setChart2D(chart2D);
	addTitleComposite();
	addLegendComposite();
	addTitles();
	addLegends();
	pack();
    }
}
