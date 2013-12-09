package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.sloc;

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

import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalyzedCode;
import com.puresoltechnologies.purifinity.client.common.evaluation.utils.ColorUtils;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCEvaluator;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCFileResults;
import com.puresoltechnologies.purifinity.coding.metrics.sloc.SLOCResult;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorStoreFactory;
import com.puresoltechnologies.purifinity.evaluation.api.SourceCodeQuality;

public class SLOCResultComponent extends Composite {

	private final AnalysisRun analysisRun;
	private final AnalyzedCode analyzedCode;

	public SLOCResultComponent(Composite parent, int style,
			AnalysisRun analysisRun, AnalyzedCode analyzedCode) {
		super(parent, style);

		this.analysisRun = analysisRun;
		this.analyzedCode = analyzedCode;

		setLayout(new FillLayout());

		EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
				.createInstance(SLOCEvaluator.class);

		SLOCFileResults fileResults = (SLOCFileResults) evaluatorStore
				.readFileResults(analyzedCode.getHashId());

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
				SLOCResult result = (SLOCResult) element;
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
				SLOCResult result = (SLOCResult) element;
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
				SLOCResult result = (SLOCResult) element;
				return result.getQuality().toString();
			}

			@Override
			public Color getBackground(Object element) {
				SLOCResult result = (SLOCResult) element;
				SourceCodeQuality quality = result.getQuality();
				return ColorUtils.getColor(getDisplay(), quality);
			}

			@Override
			public String getToolTipText(Object element) {
				SLOCResult result = (SLOCResult) element;
				return result.getSLOCMetric().toString();
			}

			@Override
			public int getToolTipDisplayDelayTime(Object element) {
				return 0;
			}

		};
		viewerQualityColumn.setLabelProvider(labelProvider);
	}

}
