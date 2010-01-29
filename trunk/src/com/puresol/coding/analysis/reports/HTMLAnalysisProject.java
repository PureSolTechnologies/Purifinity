/***************************************************************************
 *
 *   HTMLAnalysisProject.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis.reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.puresol.coding.CodeRange;
import com.puresol.coding.ProjectAnalyser;
import com.puresol.coding.analysis.CodeRangeMetrics;
import com.puresol.coding.analysis.MetricsCalculator;
import com.puresol.coding.analysis.QualityLevel;
import com.puresol.html.HTMLStandards;
import com.puresol.html.Link;
import com.puresol.jars.JarFile;

public class HTMLAnalysisProject {

    public static boolean create(File directory, ProjectAnalyser analyser) {
	return new HTMLAnalysisProject(analyser)
		.createHTMLProject(directory);
    }

    private final ProjectAnalyser analyser;
    private final MetricsCalculator metrics;
    private final Hashtable<File, String> fileIndex =
	    new Hashtable<File, String>();
    private final Hashtable<CodeRange, String> codeRangeIndex =
	    new Hashtable<CodeRange, String>();

    private HTMLAnalysisProject(ProjectAnalyser analyser) {
	this.analyser = analyser;
	this.metrics = new MetricsCalculator(analyser);
	metrics.run();
    }

    private static final Logger logger =
	    Logger.getLogger(HTMLAnalysisProject.class);

    public boolean createHTMLProject(File directory) {
	if (!directory.exists()) {
	    if (!directory.mkdir()) {
		return false;
	    }
	}
	JarFile.extractResource(HTMLAnalysisProject.class
		.getResource("/config/logo.jpeg"), new File(directory
		.getPath()
		+ "/logo.jpeg"));
	JarFile.extractResource(HTMLAnalysisProject.class
		.getResource("/css/report.css"), new File(directory
		.getPath()
		+ "/report.css"));
	createIndexHTML(directory);
	createStartHTML(directory);
	createEmptyHTML(directory);
	createFileIndex(directory);
	createCodeRangeIndizes(directory);
	createReports(directory);
	return true;
    }

    private boolean writeFile(File directory, String fileName, String html) {
	RandomAccessFile ra = null;
	try {
	    ra =
		    new RandomAccessFile(new File(directory.toString()
			    + "/" + fileName), "rw");
	    ra.setLength(0);
	    ra.writeBytes(html);
	    ra.close();
	    return true;
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	if (ra != null) {
	    try {
		ra.close();
	    } catch (IOException e) {
	    }
	}
	return false;
    }

    private boolean createIndexHTML(File directory) {
	String html = HTMLStandards.getStandardFrameSetHeader();
	html += "<frameset cols=\"20%,80%\">";
	html += "<frameset rows=\"30%,40%\">";
	html += "<frame src=\"files.html\" name=\"files\"/>";
	html += "<frame src=\"empty.html\" name=\"file\"/>";
	html += "</frameset>";
	html += "<frame src=\"start.html\" name=\"report\"/>";
	html += "</frameset>";
	html += HTMLStandards.getStandardFrameSetFooter();
	return writeFile(directory, "index.html", html);
    }

    private boolean createStartHTML(File directory) {
	String html =
		HTMLStandards.getStandardHeader("CodeAnalysis",
			"report.css", false);
	html += "<img src=\"logo.jpeg\"/> <h1>CodeAnalysis</h1>";
	html += "<p>" + HTMLStandards.getCopyright() + "</p>";
	html +=
		"<p>For more information have a look to "
			+ Link.getPureSolTechnolgiesHomePage().toHTML()
			+ "</p>";
	html += HTMLStandards.getStandardFooter();
	return writeFile(directory, "start.html", html);
    }

    private boolean createEmptyHTML(File directory) {
	String html = "<html><head></head><body></body></html>";
	return writeFile(directory, "empty.html", html);
    }

    private boolean createFileIndex(File directory) {
	String html = HTMLStandards.getStandardHeader();
	ArrayList<File> files = analyser.getFiles();
	Collections.sort(files);
	html += "<table>\n";
	int fileNum = 0;
	for (File file : files) {
	    fileNum++;
	    html += "<tr>\n";
	    html += "<td>" + fileNum + "</td>";
	    String name = file.getName();
	    if (fileIndex.containsValue(name)) {
		int index = 2;
		while (fileIndex.contains(name + index)) {
		    index++;
		}
		name = name + index;
	    }
	    fileIndex.put(file, name);
	    QualityLevel level = metrics.getQualityLevel(file);
	    if (level == QualityLevel.HIGH) {
		html += "<td bgcolor=\"green\">";
	    } else if (level == QualityLevel.MEDIUM) {
		html += "<td bgcolor=\"yellow\">";

	    } else {
		html += "<td bgcolor=\"red\">";
	    }
	    html +=
		    "<a href=\"" + name + ".html\" target=\"file\">"
			    + file.getPath() + "</a></td>\n";
	    html += "</tr>\n";
	}
	html += "</table>\n";
	html += HTMLStandards.getStandardFooter();
	return writeFile(directory, "files.html", html);
    }

    private boolean createCodeRangeIndizes(File directory) {
	for (File file : analyser.getFiles()) {
	    String html = HTMLStandards.getStandardHeader();
	    html += "<table>";
	    ArrayList<CodeRange> ranges = analyser.getCodeRanges(file);
	    Collections.sort(ranges);
	    int index = 0;
	    for (CodeRange range : ranges) {
		CodeRangeMetrics codeRangeMetrics =
			metrics.getMetrics(range);
		index++;
		codeRangeIndex.put(range, fileIndex.get(file)
			+ "_coderange" + index);
		html += "<tr>\n";
		html += "<td>" + index + "</td>";
		QualityLevel level = codeRangeMetrics.getQualityLevel();
		if (level == QualityLevel.HIGH) {
		    html += "<td bgcolor=\"green\">";
		} else if (level == QualityLevel.MEDIUM) {
		    html += "<td bgcolor=\"yellow\">";
		} else {
		    html += "<td bgcolor=\"red\">";
		}
		html +=
			"<a href=\"" + codeRangeIndex.get(range)
				+ ".html\" target=\"report\">"
				+ range.getName() + "</a></td>\n";
		html += "</tr>\n";
	    }
	    html += "</table>";
	    html += HTMLStandards.getStandardFooter();
	    if (!writeFile(directory, fileIndex.get(file) + ".html", html)) {
		return false;
	    }
	}
	return true;
    }

    private boolean createReports(File directory) {
	for (File file : analyser.getFiles()) {
	    ArrayList<CodeRange> ranges = analyser.getCodeRanges(file);
	    for (CodeRange range : ranges) {
		CodeRangeMetrics codeRangeMetrics =
			metrics.getMetrics(range);
		HTMLAnalysisReport report =
			new HTMLAnalysisReport(codeRangeMetrics);
		if (!writeFile(directory, codeRangeIndex.get(range)
			+ ".html", report.getReport("report.css", false))) {
		    return false;
		}
	    }
	}
	return true;
    }

}
