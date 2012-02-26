package com.puresol.gui.coding;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.coding.CodeRange;
import com.puresol.coding.analysis.Analysis;
import com.puresol.coding.analysis.AnalyzedFile;
import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.Result;
import com.puresol.config.Configuration;
import com.puresol.gui.Application;
import com.puresol.gui.progress.FinishListener;
import com.puresol.gui.progress.ProgressObservable;
import com.puresol.gui.progress.ProgressWindow;
import com.puresol.utils.PersistenceException;

/**
 * This GUI element lists all available project analyzers and enables the user
 * to run them and to explore the results.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ProjectGraphPanel extends JPanel implements ListSelectionListener,
	ActionListener, FinishListener {

    private static final long serialVersionUID = 7855693564694783199L;

    private static final Logger logger = LoggerFactory
	    .getLogger(ProjectGraphPanel.class);

    private final Configuration configuration;
    private ProjectAnalyzer projectAnalyzer = null;

    private final ProjectEvaluatorChooser evaluators = new ProjectEvaluatorChooser();
    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JButton showButton = new JButton("Show...");

    public ProjectGraphPanel(Configuration configuration) {
	super();
	this.configuration = configuration;
	initUI();
    }

    public ProjectGraphPanel(ProjectAnalyzer projectAnalyzer,
	    Configuration configuration) {
	super();
	this.projectAnalyzer = projectAnalyzer;
	this.configuration = configuration;
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
	ProjectEvaluatorFactory evaluatorFactory = (ProjectEvaluatorFactory) evaluators
		.getSelectedValue();
	Evaluator evaluator = evaluatorFactory.create(projectAnalyzer,
		configuration);
	ProgressWindow progress = new ProgressWindow(
		SwingUtilities.getWindowAncestor(this), true);
	progress.addFinishListener(this);
	progress.runAsynchronous(evaluator);
    }

    @Override
    public void finished(ProgressObservable observable) {
	Evaluator evaluator = (Evaluator) observable;
	Map<String, DefaultCategoryDataset> datasets = new HashMap<String, DefaultCategoryDataset>();
	for (AnalyzedFile analyzedFile : projectAnalyzer.getAnalyzedFiles()) {
	    Analysis analysis = projectAnalyzer.getAnalysis(analyzedFile);
	    for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
		for (Result result : evaluator.getResults()) {
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
		    PlotOrientation.VERTICAL, true, false, false);
	    chart.setAntiAlias(true);
	    chart.setTextAntiAlias(false);
	    ChartPanel panel = new ChartPanel(chart);
	    tabbedPane.add(panel, evaluator.getName() + " - " + name);
	}
    }

    @Override
    public void terminated(ProgressObservable observable) {
	int result = JOptionPane
		.showConfirmDialog(
			Application.getInstance(),
			"Evaluator calcualtion was aborted. The results are now not completed and may be wrong.\n"
				+ "Do you want to have them displayed anyway?",
			"Caluclation aborted", JOptionPane.YES_NO_OPTION,
			JOptionPane.WARNING_MESSAGE);
	if (result == JOptionPane.YES_OPTION) {
	    finished(observable);
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
