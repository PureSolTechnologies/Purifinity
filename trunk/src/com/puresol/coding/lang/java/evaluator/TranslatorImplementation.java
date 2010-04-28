package com.puresol.coding.lang.java.evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.Translator;
import javax.swingx.progress.ProgressObserver;

import com.puresol.coding.analysis.Analyser;
import com.puresol.coding.analysis.CodeRange;
import com.puresol.coding.analysis.ProjectAnalyser;
import com.puresol.coding.evaluator.AbstractEvaluator;
import com.puresol.coding.evaluator.QualityLevel;
import com.puresol.coding.lang.java.Java;
import com.puresol.coding.lang.java.source.parts.ClassDeclaration;
import com.puresol.coding.langelements.VariableLanguageElement;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.Property;

public class TranslatorImplementation extends AbstractEvaluator {

    private static final long serialVersionUID = 7618097301047375380L;

    private static final Translator translator = Translator
	    .getTranslator(TranslatorImplementation.class);

    public static final String NAME = "Translator Implementation for Java";
    public static final String DESCRIPTION = translator
	    .i18n("This evaluator checks for a Translator implementation"
		    + "recommended by PureSol-Technologies.");
    public static final ArrayList<Property> SUPPORTED_PROPERTIES = new ArrayList<Property>();

    private static List<CodeRange> failed = new ArrayList<CodeRange>();

    public TranslatorImplementation(ProjectAnalyser analyser) {
	super(analyser);
    }

    @Override
    public void run() {
	ProgressObserver observer = getMonitor();
	if (observer != null) {
	    observer.setDescription(NAME);
	    observer.setRange(0, getProjectAnalyser().getFiles().size());
	    observer.setStatus(0);
	}
	int count = 0;
	ProjectAnalyser projectAnalyser = getProjectAnalyser();
	for (File file : projectAnalyser.getFiles()) {
	    if (Thread.interrupted()) {
		return;
	    }
	    count++;
	    if (observer != null) {
		observer.setStatus(count);
	    }
	    Analyser analyser = projectAnalyser.getAnalyser(file);
	    for (CodeRange codeRange : analyser.getNamedCodeRanges()) {
		if (Thread.interrupted()) {
		    return;
		}
		if (codeRange.getLanguage() != Java.getInstance()) {
		    continue;
		}
		if (!codeRange.getClass().equals(ClassDeclaration.class)) {
		    continue;
		}
		addFile(file);
		addCodeRange(codeRange);
		analyse(codeRange);
	    }
	}
	if (observer != null) {
	    observer.finish();
	}
    }

    private void analyse(CodeRange codeRange) {
	ClassDeclaration classDeclaration = (ClassDeclaration) codeRange;
	for (VariableLanguageElement field : classDeclaration.getFields()) {
	    if (!(field.getVariableType().equals(
		    Translator.class.getSimpleName()) || field
		    .getVariableType().equals(Translator.class.getName()))) {
		continue;
	    }
	    List<String> modifiers = field.getModifiers();
	    System.err.println(codeRange.getText());
	    if (modifiers.size() != 3) {
		failed.add(codeRange);
		continue;
	    }
	    if (!(modifiers.get(0).equals("private")
		    && modifiers.get(1).equals("static") && modifiers.get(2)
		    .equals("final"))) {
		failed.add(codeRange);
		continue;
	    }
	    if (!field.getName().equals("translator")) {
		failed.add(codeRange);
		continue;
	    }
	}
    }

    @Override
    public String getName() {
	return NAME;
    }

    @Override
    public String getDescription(ReportingFormat format) {
	if (format == ReportingFormat.HTML) {
	    return HTMLStandards.convertFlowTextToHTML(DESCRIPTION);
	}
	return DESCRIPTION;
    }

    @Override
    public QualityLevel getQuality(File file) {
	return QualityLevel.UNSPECIFIED;
    }

    @Override
    public String getFileComment(File file, ReportingFormat format) {
	return "";
    }

    @Override
    public String getProjectComment(ReportingFormat format) {
	return "";
    }

    @Override
    public QualityLevel getProjectQuality() {
	return QualityLevel.UNSPECIFIED;
    }

    @Override
    public String getCodeRangeComment(CodeRange codeRange,
	    ReportingFormat format) {
	return "";
    }

    @Override
    public QualityLevel getQuality(CodeRange codeRange) {
	return QualityLevel.UNSPECIFIED;
    }

}
