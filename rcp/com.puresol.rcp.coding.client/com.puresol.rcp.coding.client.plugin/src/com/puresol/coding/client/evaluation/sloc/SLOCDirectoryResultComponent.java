package com.puresol.coding.client.evaluation.sloc;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import com.puresol.coding.analysis.api.AnalysisRun;
import com.puresol.coding.analysis.api.HashIdFileTree;
import com.puresol.coding.evaluation.api.EvaluatorStore;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.metrics.sloc.SLOCDirectoryResult;
import com.puresol.coding.metrics.sloc.SLOCDirectoryResults;
import com.puresol.coding.metrics.sloc.SLOCEvaluator;

public class SLOCDirectoryResultComponent extends Composite {

    private final AnalysisRun analysisRun;
    private final HashIdFileTree directory;

    public SLOCDirectoryResultComponent(Composite parent, int style,
	    AnalysisRun analysisRun, HashIdFileTree directory) {
	super(parent, style);

	this.analysisRun = analysisRun;
	this.directory = directory;

	setLayout(new FillLayout());

	EvaluatorStore evaluatorStore = AbstractEvaluator
		.createEvaluatorStore(SLOCEvaluator.class);

	SLOCDirectoryResults directoryResults = (SLOCDirectoryResults) evaluatorStore
		.readDirectoryResults(directory.getHashId());

	Table table = new Table(this, SWT.BORDER | SWT.V_SCROLL
		| SWT.FULL_SELECTION);
	table.setHeaderVisible(true);
	table.setLinesVisible(true);

	TableViewer tableViewer = new TableViewer(table);
	tableViewer.setContentProvider(ArrayContentProvider.getInstance());
	ColumnViewerToolTipSupport.enableFor(tableViewer);

	addFileColumn(tableViewer);
	addPhyLOCColumn(tableViewer);
	addProLOCColumn(tableViewer);
	addComLOCColumn(tableViewer);
	addBlLOCColumn(tableViewer);

	tableViewer.setInput(directoryResults);
    }

    private void addFileColumn(TableViewer tableViewer) {
	TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerRangeTypeColumn.getColumn().setText("File");
	viewerRangeTypeColumn.getColumn().setWidth(200);
	viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCDirectoryResult result = (SLOCDirectoryResult) element;
		return result.getFile();
	    }
	});
    }

    private void addPhyLOCColumn(TableViewer tableViewer) {
	TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerRangeTypeColumn.getColumn().setText("phyLOC");
	viewerRangeTypeColumn.getColumn().setWidth(200);
	viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCDirectoryResult result = (SLOCDirectoryResult) element;
		return String.valueOf(result.getSLOCResult().getPhyLOC());
	    }
	});
    }

    private void addProLOCColumn(TableViewer tableViewer) {
	TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerRangeTypeColumn.getColumn().setText("proLOC");
	viewerRangeTypeColumn.getColumn().setWidth(200);
	viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCDirectoryResult result = (SLOCDirectoryResult) element;
		return String.valueOf(result.getSLOCResult().getProLOC());
	    }
	});
    }

    private void addComLOCColumn(TableViewer tableViewer) {
	TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerRangeTypeColumn.getColumn().setText("comLOC");
	viewerRangeTypeColumn.getColumn().setWidth(200);
	viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCDirectoryResult result = (SLOCDirectoryResult) element;
		return String.valueOf(result.getSLOCResult().getComLOC());
	    }
	});
    }

    private void addBlLOCColumn(TableViewer tableViewer) {
	TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
		tableViewer, SWT.NONE);
	viewerRangeTypeColumn.getColumn().setText("blLOC");
	viewerRangeTypeColumn.getColumn().setWidth(200);
	viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

	    @Override
	    public String getText(Object element) {
		SLOCDirectoryResult result = (SLOCDirectoryResult) element;
		return String.valueOf(result.getSLOCResult().getBlLOC());
	    }
	});
    }
}
