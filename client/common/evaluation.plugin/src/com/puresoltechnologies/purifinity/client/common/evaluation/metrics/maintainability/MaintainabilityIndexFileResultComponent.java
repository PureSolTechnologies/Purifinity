package com.puresoltechnologies.purifinity.client.common.evaluation.metrics.maintainability;

import org.eclipse.core.runtime.Status;
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
import org.osgi.framework.Bundle;

import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.client.common.evaluation.Activator;
import com.puresoltechnologies.purifinity.client.common.evaluation.utils.ColorUtils;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexEvaluator;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexFileResult;
import com.puresoltechnologies.purifinity.framework.evaluation.metrics.maintainability.MaintainabilityIndexFileResults;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStore;
import com.puresoltechnologies.purifinity.framework.store.api.EvaluatorStoreFactory;

public class MaintainabilityIndexFileResultComponent extends Composite {

	public MaintainabilityIndexFileResultComponent(Composite parent, int style,
			AnalysisInformation analyzedCode) {
		super(parent, style);
		try {
			setLayout(new FillLayout());

			EvaluatorStore evaluatorStore = EvaluatorStoreFactory.getFactory()
					.createInstance(MaintainabilityIndexEvaluator.class);

			MaintainabilityIndexFileResults fileResults = (MaintainabilityIndexFileResults) evaluatorStore
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
		} catch (EvaluationStoreException e) {
			Activator activator = Activator.getDefault();
			Bundle bundle = activator.getBundle();
			activator.getLog().log(
					new Status(Status.ERROR, bundle.getSymbolicName(),
							"Could not read results.", e));
		}
	}

	private void addCodeRangeTypeColumn(TableViewer tableViewer) {
		TableViewerColumn viewerRangeTypeColumn = new TableViewerColumn(
				tableViewer, SWT.NONE);
		viewerRangeTypeColumn.getColumn().setText("Part Type");
		viewerRangeTypeColumn.getColumn().setWidth(200);
		viewerRangeTypeColumn.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public String getText(Object element) {
				MaintainabilityIndexFileResult result = (MaintainabilityIndexFileResult) element;
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
				MaintainabilityIndexFileResult result = (MaintainabilityIndexFileResult) element;
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
				MaintainabilityIndexFileResult result = (MaintainabilityIndexFileResult) element;
				return result.getQuality().toString();
			}

			@Override
			public Color getBackground(Object element) {
				MaintainabilityIndexFileResult result = (MaintainabilityIndexFileResult) element;
				SourceCodeQuality quality = result.getQuality();
				return ColorUtils.getColor(getDisplay(), quality);
			}

			@Override
			public String getToolTipText(Object element) {
				MaintainabilityIndexFileResult result = (MaintainabilityIndexFileResult) element;
				return result.getMaintainabilityIndexResult().toString();
			}

			@Override
			public int getToolTipDisplayDelayTime(Object element) {
				return 0;
			}

		};
		viewerQualityColumn.setLabelProvider(labelProvider);
	}

}
