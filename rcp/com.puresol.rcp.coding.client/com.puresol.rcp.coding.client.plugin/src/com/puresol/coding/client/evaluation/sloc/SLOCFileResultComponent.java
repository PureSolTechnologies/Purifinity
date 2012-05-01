package com.puresol.coding.client.evaluation.sloc;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.AnalyzedFile;
import com.puresol.coding.client.utils.ColorUtils;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;
import com.puresol.coding.metrics.sloc.SLOCFileResult;
import com.puresol.coding.metrics.sloc.SLOCFileResults;
import com.puresol.coding.quality.api.SourceCodeQuality;

public class SLOCFileResultComponent extends Composite {

    private final AnalysisRun analysisRun;
    private final AnalyzedFile analyzedFile;

    public SLOCFileResultComponent(Composite parent, int style,
	    AnalysisRun analysisRun, AnalyzedFile analyzedFile) {
	super(parent, style);

	this.analysisRun = analysisRun;
	this.analyzedFile = analyzedFile;

	setLayout(new FillLayout());

	EvaluatorStore evaluatorStore = AbstractEvaluator
		.createEvaluatorStore(SLOCEvaluator.class);

	SLOCFileResults fileResults = (SLOCFileResults) evaluatorStore
		.readFileResults(analyzedFile.getHashId());

	Table table = new Table(this, SWT.BORDER | SWT.V_SCROLL
		| SWT.FULL_SELECTION);
	table.setHeaderVisible(true);
	table.setLinesVisible(true);

	TableViewer tableViewer = new TableViewer(table);
	tableViewer.setContentProvider(ArrayContentProvider.getInstance());
	ColumnViewerToolTipSupport.enableFor(tableViewer);

	addCodeRangeTypeColumn(tableViewer);
	addCodeRangeNameColumn(tableViewer);
	addQualityColumn(tableViewer);

	tableViewer.setInput(fileResults);
    }

    private void addCodeRangeTypeColumn(TableViewer tableViewer) {
	TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerRangeTypeColumn.getColumn().setText("Part Type");
	viewerRangeTypeColumn.getColumn().setWidth(200);
	viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCFileResult result = (SLOCFileResult) element;
		return result.getCodeRangeType().getName();
	    }
	});
    }

    private void addCodeRangeNameColumn(TableViewer tableViewer) {
	TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerRangeTypeColumn.getColumn().setText("Part Name");
	viewerRangeTypeColumn.getColumn().setWidth(200);
	viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCFileResult result = (SLOCFileResult) element;
		return result.getCodeRangeName();
	    }
	});
    }

    private void addQualityColumn(TableViewer tableViewer) {
	TableViewerColumn viewerQualityColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerQualityColumn.getColumn().setText("Quality");
	viewerQualityColumn.getColumn().setWidth(400);
	ColumnLabelProvider labelProvider = new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCFileResult result = (SLOCFileResult) element;
		return result.getQuality().toString();
	    }

	    @Override
	    public Color getBackground(Object element) {
		SLOCFileResult result = (SLOCFileResult) element;
		SourceCodeQuality quality = result.getQuality();
		return ColorUtils.getColor(getDisplay(), quality);
	    }

	    @Override
	    public String getToolTipText(Object element) {
		SLOCFileResult result = (SLOCFileResult) element;
		return result.getSLOCResult().toString();
	    }

	    @Override
	    public int getToolTipDisplayDelayTime(Object element) {
		return 0;
	    }

	};
	viewerQualityColumn.setLabelProvider(labelProvider);
    }

}