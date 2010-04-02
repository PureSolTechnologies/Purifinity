package com.puresol.coding.analysis.reports;

import java.io.File;

import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.html.HTMLStandards;
import com.puresol.jars.JarFile;
import com.puresol.utils.Directories;
import com.puresol.utils.Files;

public class HTMLProject {

	public static boolean create(File directory, ProjectAnalyser analyser) {
		return new HTMLProject(directory, analyser).createHTMLProject();
	}

	private final ProjectAnalyser analyser;
	private final File directory;

	private HTMLProject(File directory, ProjectAnalyser analyser) {
		this.directory = directory;
		this.analyser = analyser;
	}

	private boolean createHTMLProject() {
		if (!Directories.checkAndCreateDirectory(directory)) {
			return false;
		}
		createProjectFrame();
		createMetrics();
		return true;
	}

	private void createProjectFrame() {
		JarFile.extractResource(HTMLMetricsProject.class
				.getResource("/config/logo.jpeg"), new File(directory.getPath()
				+ "/logo.jpeg"));
		JarFile.extractResource(HTMLMetricsProject.class
				.getResource("/css/report.css"), new File(directory.getPath()
				+ "/report.css"));
		createIndexHTML();
		createMenuHTML();
		ReportStandards.createStartHTML(directory, "CodeAnalysis");
	}

	private boolean createMetrics() {
		return HTMLMetricsProject.create(Files.addPaths(directory, new File(
				"metrics")), analyser);
	}

	private boolean createIndexHTML() {
		String html = HTMLStandards.getStandardFrameSetHeader();
		html += "<frameset rows=\"10%,90%\">";
		html += "<frame src=\"menu.html\" name=\"menu\"/>";
		html += "<frame src=\"start.html\" name=\"desktop\"/>";
		html += "</frameset>";
		html += "</body></html>";
		return Files.writeFile(directory, "index.html", html);
	}

	private boolean createMenuHTML() {
		String html = HTMLStandards.getStandardHeader();
		html += "<a href=\"start.html\" target=\"desktop\">Home</a>";
		html += " | <a href=\"metrics/index.html\" target=\"desktop\">Metrics</a>";
		html += " | <a href=\"duplications/index.html\" target=\"desktop\">Duplications</a>";
		html += HTMLStandards.getStandardFooter();
		return Files.writeFile(directory, "menu.html", html);
	}
}
