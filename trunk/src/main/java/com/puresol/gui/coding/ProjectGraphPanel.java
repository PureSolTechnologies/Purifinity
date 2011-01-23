package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.Result;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

/**
 * This GUI element lists all available project analyzers and enables the user
 * to run them and to explore the results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectGraphPanel extends JPanel implements ListSelectionListener,
		ActionListener {

	private static final long serialVersionUID = 7855693564694783199L;

	private static final Logger logger = Logger
			.getLogger(ProjectGraphPanel.class);

	private ProjectAnalyzer projectAnalyzer = null;

	private final ProjectEvaluatorChooser evaluators = new ProjectEvaluatorChooser();
	private final JTabbedPane tabbedPane = new JTabbedPane();
	private final JButton showButton = new JButton("Show...");

	public ProjectGraphPanel() {
		super();
		initUI();
	}

	public ProjectGraphPanel(ProjectAnalyzer projectAnalyzer) {
		super();
		this.projectAnalyzer = projectAnalyzer;
		initUI();
	}

	private void initUI() {
		setLayout(new BorderLayout());

		evaluators.addListSelectionListener(this);
		showButton.addActionListener(this);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				true, new JScrollPane(evaluators), tabbedPane);

		add(splitPane, BorderLayout.CENTER);
		add(showButton, BorderLayout.NORTH);
	}

	public ProjectAnalyzer getProjectAnlayzer() {
		return projectAnalyzer;
	}

	public void setProjectAnalyzer(ProjectAnalyzer projectAnalyzer) {
		this.projectAnalyzer = projectAnalyzer;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if ((e.getSource() == evaluators) && (projectAnalyzer != null)) {
		}
	}

	private void showDiagrams() throws IOException, PersistenceException {
		Map<String, DefaultCategoryDataset> datasets = new HashMap<String, DefaultCategoryDataset>();
		ProjectEvaluatorFactory evaluatorFactory = (ProjectEvaluatorFactory) evaluators
				.getSelectedValue();
		Evaluator evaluator = evaluatorFactory.create(projectAnalyzer);
		for (AnalyzedFile analyzedFile : projectAnalyzer.getAnalyzedFiles()) {
			Analysis analysis = projectAnalyzer.getAnalysis(analyzedFile);
			for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
				@SuppressWarnings("unchecked")
				List<Result> results = (List<Result>) Persistence
						.restore(analyzedFile.getResultsFile(
								evaluatorFactory.getProjectEvaluatorClass(),
								codeRange));
				for (Result result : results) {
					DefaultCategoryDataset dataset = datasets.get(result
							.getName());
					if (dataset == null) {
						dataset = new DefaultCategoryDataset();
						datasets.put(result.getName(), dataset);
					}
					dataset.addValue(Double.valueOf(result.getValue()),
							codeRange.getType().getName(),
							analyzedFile.getFile());
				}
			}
		}
		tabbedPane.removeAll();
		for (String name : datasets.keySet()) {
			JFreeChart chart = ChartFactory.createLineChart(evaluator.getName()
					+ " - " + name, "code range", "value", datasets.get(name),
					PlotOrientation.VERTICAL, true, true, true);
			chart.setAntiAlias(false);
			chart.setTextAntiAlias(false);
			ChartPanel panel = new ChartPanel(chart);
			tabbedPane.add(panel, evaluator.getName() + " - " + name);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			showDiagrams();
		} catch (IOException e1) {
			logger.error(e1.getMessage(), e1);
		} catch (PersistenceException e2) {
			logger.error(e2.getMessage(), e2);
		}
	}

}
