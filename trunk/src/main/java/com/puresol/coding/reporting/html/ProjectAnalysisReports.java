package com.puresol.coding.reporting.html;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.i18n4java.Translator;

import org.apache.log4j.Logger;

import com.puresol.coding.analysis.ProjectAnalyzer;
import com.puresol.coding.evaluator.Evaluator;
import com.puresol.coding.evaluator.ProjectEvaluator;
import com.puresol.coding.evaluator.ProjectEvaluatorFactory;
import com.puresol.coding.evaluator.ProjectEvaluatorManager;
import com.puresol.config.Configuration;
import com.puresol.document.convert.html.HTMLConverter;
import com.puresol.gui.progress.ProgressObserver;
import com.puresol.gui.progress.ProgressPanel;
import com.puresol.gui.progress.RunnableProgressObservable;

class ProjectAnalysisReports implements RunnableProgressObservable {

	private static final Logger logger = Logger
			.getLogger(ProjectAnalysisReports.class);
	private static final Translator translator = Translator
			.getTranslator(ProjectAnalysisReports.class);

	private final Configuration configuration;
	private final ProjectAnalyzer projectAnalyzer;
	private final File directory;
	private ProgressObserver monitor = null;

	public ProjectAnalysisReports(ProjectAnalyzer projectAnalyzer,
			File directory, Configuration configuration) {
		super();
		this.projectAnalyzer = projectAnalyzer;
		this.directory = directory;
		this.configuration = configuration;
	}

	@Override
	public void setMonitor(ProgressObserver observer) {
		this.monitor = observer;
	}

	@Override
	public ProgressObserver getMonitor() {
		return monitor;
	}

	@Override
	public synchronized void run() {
		try {
			List<ProjectEvaluatorFactory> evaluatorFactories = ProjectEvaluatorManager
					.getAll();
			if (monitor != null) {
				monitor.setTitle(translator
						.i18n("Create Project Evaluation Pages..."));
				monitor.showProgressPercent();
				monitor.setRange(0, evaluatorFactories.size() - 1);
				monitor.setStatus(0);
			}
			createProjectEvaluatorIndex();
			for (int index = 0; index < evaluatorFactories.size(); index++) {
				ProjectEvaluatorFactory projectEvaluatorFactory = evaluatorFactories
						.get(index);
				if (monitor != null) {
					monitor.setStatus(index);
					monitor.setText(projectEvaluatorFactory.getName());
				}
				processProjectEvaluator(projectEvaluatorFactory);
				if (Thread.interrupted()) {
					if (monitor != null) {
						monitor.terminated(this);
					}
					return;
				}
			}
			if (monitor != null) {
				monitor.finished(this);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			if (monitor != null) {
				monitor.terminated(this);
			}
		}
	}

	private void createProjectEvaluatorIndex() throws IOException {
		File file = new File(new File(directory, "project"), "index.html");
		HTMLReport indexFile = new HTMLReport(file,
				HTMLProjectAnalysisCreator.getCSSFile(directory),
				HTMLProjectAnalysisCreator.getLogoFile(directory),
				HTMLProjectAnalysisCreator.getFavIconFile(directory),
				translator.i18n("Project Analysis"));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile
					.write(MainMenu.getHTML(directory, file, MainMenu.PROJECT));
			indexFile.write(getEvaluatorMenu(null));
			// TODO
		} finally {
			indexFile.close();
		}
	}

	private void processProjectEvaluator(
			ProjectEvaluatorFactory projectEvaluatorFactory)
			throws InterruptedException, IOException {
		ProjectEvaluator evaluator = projectEvaluatorFactory.create(
				projectAnalyzer, configuration);
		ProgressPanel panel = monitor.getSubProgressPanel();
		panel.runSyncronous(evaluator);

		File file = new File(new File(directory, "project"),
				evaluator.getName() + ".html");
		HTMLReport indexFile = new HTMLReport(file,
				HTMLProjectAnalysisCreator.getCSSFile(directory),
				HTMLProjectAnalysisCreator.getLogoFile(directory),
				HTMLProjectAnalysisCreator.getFavIconFile(directory),
				translator.i18n("Project Analysis"));
		try {
			indexFile.setCopyrightFooter(true);
			indexFile
					.write(MainMenu.getHTML(directory, file, MainMenu.PROJECT));

			indexFile.write("<table>\n");
			indexFile.write("<tr>\n");
			indexFile.write("<td valign=\"top\">\n");

			indexFile.write(getEvaluatorMenu(evaluator));

			indexFile.write("</td>\n");
			indexFile.write("<td>\n");

			indexFile.write(new HTMLConverter(evaluator.getReport(), indexFile
					.getFile().getParentFile()).toHTML(false));

			indexFile.write("</td>\n");
			indexFile.write("</tr>\n");
			indexFile.write("</table>\n");
		} finally {
			indexFile.close();
		}
	}

	private String getEvaluatorMenu(Evaluator currentEvaluator) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("<table>\n");
		List<ProjectEvaluatorFactory> evaluatorFactories = ProjectEvaluatorManager
				.getAll();
		for (ProjectEvaluatorFactory evaluatorFactory : evaluatorFactories) {
			if ((currentEvaluator != null)
					&& (evaluatorFactory.getName().equals(currentEvaluator
							.getName()))) {
				buffer.append("<tr><td>\n");
				buffer.append(evaluatorFactory.getName());
				buffer.append("</td></tr>\n");
			} else {
				buffer.append("<tr><td>\n");
				buffer.append("<a href=\"" + evaluatorFactory.getName()
						+ ".html\">\n");
				buffer.append(evaluatorFactory.getName());
				buffer.append("</a>\n");
				buffer.append("</td></tr>\n");
			}
		}
		buffer.append("</table>\n");
		return buffer.toString();
	}

}
