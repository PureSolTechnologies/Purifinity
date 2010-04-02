package com.puresol.coding.analysis.reports;

import java.io.File;

import javax.swingx.data.VerticalData;
import javax.swingx.data.VerticalDataFile;
import javax.swingx.data.VerticalDataFileFormat;

import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.metric.CodeDepth;
import com.puresol.coding.evaluator.metric.CodeRangeMetrics;
import com.puresol.coding.evaluator.metric.HalsteadMetric;
import com.puresol.coding.evaluator.metric.MaintainabilityIndex;
import com.puresol.coding.evaluator.metric.McCabeMetric;
import com.puresol.coding.evaluator.metric.MetricsCalculator;
import com.puresol.coding.evaluator.metric.SLOCMetric;
import com.puresol.data.Storable;
import com.puresol.data.StoreType;

public class TSVFile implements Storable {

	public static void create(File file, ProjectAnalyser analyser) {
		new TSVFile(analyser).store(file);
	}

	private final ProjectAnalyser analyser;
	private final MetricsCalculator metrics;

	public TSVFile(ProjectAnalyser analyser) {
		this.analyser = analyser;
		this.metrics = new MetricsCalculator(analyser);
	}

	@Override
	public void store(File file) {
		VerticalDataFile raFile = new VerticalDataFile();
		raFile.setFileFormat(VerticalDataFileFormat.TAB_SEPARATED);
		VerticalData verticalData = getVerticalData();
		raFile.write(file, verticalData);
	}

	private VerticalData getVerticalData() {
		VerticalData verticalData = new VerticalData();
		verticalData.addColumn("File", File.class);
		verticalData.addColumn("RangeType", String.class);
		verticalData.addColumn("Name", String.class);
		verticalData.addColumn("SLOC phyLOC", Integer.class);
		verticalData.addColumn("SLOC proLOC", Integer.class);
		verticalData.addColumn("SLOC comLOC", Integer.class);
		verticalData.addColumn("SLOC blLOC", Integer.class);
		verticalData.addColumn("Code depth", Integer.class);
		verticalData.addColumn("McCabe v(G)", Integer.class);
		verticalData.addColumn("Halstead n1", Integer.class);
		verticalData.addColumn("Halstead N1", Integer.class);
		verticalData.addColumn("Halstead n2", Integer.class);
		verticalData.addColumn("Halstead N2", Integer.class);
		verticalData.addColumn("Halstead n", Double.class);
		verticalData.addColumn("Halstead N", Double.class);
		verticalData.addColumn("Halstead HL", Double.class);
		verticalData.addColumn("Halstead HV", Double.class);
		verticalData.addColumn("Halstead D", Double.class);
		verticalData.addColumn("Halstead L", Double.class);
		verticalData.addColumn("Halstead E", Double.class);
		verticalData.addColumn("Halstead T", Double.class);
		verticalData.addColumn("Halstead B", Double.class);
		verticalData.addColumn("Maintainability MIwoc", Double.class);
		verticalData.addColumn("Maintainability MIcw", Double.class);
		verticalData.addColumn("Maintainability MI", Double.class);
		for (File sourceFile : analyser.getFiles()) {
			processFile(verticalData, sourceFile);
		}
		return verticalData;
	}

	private void processFile(VerticalData data, File file) {
		for (CodeRange range : analyser.getCodeRanges(file)) {
			processCodeRange(data, file, range);
		}
	}

	private void processCodeRange(VerticalData data, File file, CodeRange range) {
		CodeRangeMetrics metrics = this.metrics.getMetrics(range);
		SLOCMetric sloc = (SLOCMetric) metrics.getMetric(SLOCMetric.class);
		McCabeMetric mcCabe = (McCabeMetric) metrics
				.getMetric(McCabeMetric.class);
		HalsteadMetric halstead = (HalsteadMetric) metrics
				.getMetric(HalsteadMetric.class);
		MaintainabilityIndex maintainability = (MaintainabilityIndex) metrics
				.getMetric(MaintainabilityIndex.class);
		CodeDepth codeDepth = (CodeDepth) metrics.getMetric(CodeDepth.class);
		int phyLOC = sloc.getPhyLOC();
		int proLOC = sloc.getProLOC();
		int comLOC = sloc.getComLOC();
		int blLOC = sloc.getBlLOC();
		int depth = codeDepth.getMaxLayer();
		int vG = mcCabe.getCyclomaticNumber();
		int n1 = halstead.get_n1();
		int N1 = halstead.get_N1();
		int n2 = halstead.get_n2();
		int N2 = halstead.get_N2();
		double n = halstead.get_n();
		double N = halstead.get_N();
		double HL = halstead.get_HL();
		double HV = halstead.get_HV();
		double D = halstead.get_D();
		double L = halstead.get_L();
		double E = halstead.get_E();
		double T = halstead.get_T();
		double B = halstead.get_B();
		double MIwoc = maintainability != null ? maintainability.getMIWoc()
				: 100.0;
		double MIcw = maintainability != null ? maintainability.getMIcw()
				: 100.0;
		double MI = maintainability != null ? maintainability.getMI() : 100.0;

		data.addRow(file, range.getType().getIdentifier(), range.getName(),
				phyLOC, proLOC, comLOC, blLOC, depth, vG, n1, N1, n2, N2, n, N,
				HL, HV, D, L, E, T, B, MIwoc, MIcw, MI);
	}

	@Override
	public StoreType getStoreType() {
		return StoreType.SINGLE_FILE;
	}
}
