package com.puresoltechnologies.purifinity.server.plugin.fortran2008.issues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorInformation;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.api.issues.IssueEvaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.CodeRangeIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Issue;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.IssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssuesImpl;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.AbstractIssueEvaluator;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStoreRemote;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.HadoopClientHelper;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Evaluator.class)
public class GNUFortranEvaluator extends AbstractIssueEvaluator implements IssueEvaluator {

    public static final String ID = GNUFortranEvaluator.class.getName();
    public static final String NAME = "GNU Fortran Evaluator";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
    public static final String DESCRIPTION = "This evaluator uses the output of the pre-analysis script to check for compiler warnings.";
    public static final QualityCharacteristic[] CHARACTERISTICS = new QualityCharacteristic[] {
	    QualityCharacteristic.INTEROPERABILITY, QualityCharacteristic.ANALYSABILITY, };
    public static final ConfigurationParameter<?>[] CONFIGURATION_PARAMETERS = new ConfigurationParameter[] {};
    public static final Set<String> DEPENDENCIES = new HashSet<>();
    private static final EvaluatorInformation INFORMATION = new EvaluatorInformation(ID, NAME, PLUGIN_VERSION,
	    EvaluatorType.DESGIN, DESCRIPTION);

    private static final IssueParameter REAL_EQUALITY_CHECK = new IssueParameter("RealEqualityCheck", "",
	    "Real numbers should not be compared for equality due to floating point rounding issues.");
    private static final IssueParameter REAL_INEQUALITY_CHECK = new IssueParameter("RealInequalityCheck", "",
	    "Real numbers should not be compared for inequality due to floating point rounding issues.");
    private static final IssueParameter UNUSED_MODULE_VARIABLE = new IssueParameter("UnusedModuleVariable", "",
	    "An imported module variable is no used.");
    private static final IssueParameter NONCONFORMING_TAB_CHARACTER = new IssueParameter("NonconformingTabCharacter",
	    "", "A nonconforming tab character was found in source.");
    private static final IssueParameter NONCONFORMING_FIXED_FORM = new IssueParameter(
	    "NonconformingTabCharacterInFixedForm", "",
	    "A nonconforming tab character was found in fixed form line prefix.");
    private static final IssueParameter OBSOLESCENT_CHARACTER_LENGTH = new IssueParameter("ObsolescentCharacterLength",
	    "", "Obsolescent feature of character length found.");
    private static final IssueParameter UNUSED_VARIABLE = new IssueParameter("UnusedVariable", "",
	    "A declared variable is not used.");
    private static final IssueParameter UNUSED_DUMMY_PARAMETER = new IssueParameter("UnusedDummyParameter", "",
	    "A declared variable is not used.");
    private static final IssueParameter UNUSED_LABEL = new IssueParameter("UnusedLabel", "",
	    "A declared label is not used.");

    public static final IssueParameter[] PARAMETERS = new IssueParameter[] { REAL_EQUALITY_CHECK, REAL_INEQUALITY_CHECK,
	    UNUSED_MODULE_VARIABLE, NONCONFORMING_TAB_CHARACTER, NONCONFORMING_FIXED_FORM, OBSOLESCENT_CHARACTER_LENGTH,
	    UNUSED_VARIABLE, UNUSED_DUMMY_PARAMETER, UNUSED_LABEL };

    static final Pattern START_COMPILE_LINE_PATTERN = Pattern.compile("f95\\s.*-c\\s+(\\S+)");
    static final Pattern POSITION_LINE_PATTERN = Pattern.compile("^(\\S+.*\\S+):(\\d+):(\\d+):\\s*");
    static final Pattern UNUSED_MODULE_VARIABLE_PATTERN = Pattern
	    .compile("Warning: Unused module variable ‘([^’]+)’ which has been explicitly imported at");
    static final Pattern NON_CONFORMING_TAB_PATTERN = Pattern
	    .compile("Warning: Nonconforming tab character in column (\\d+) of line (\\d+)");
    static final Pattern UNUSED_VARIABLE_PATTERN = Pattern.compile("Warning: Unused variable ‘([^’]+)’ declared");
    static final Pattern UNUSED_DUMMY_ARGUMENT_PATTERN = Pattern.compile("Warning: Unused dummy argument ‘([^’]+)’");
    static final Pattern UNUSED_LABEL_PATTERN = Pattern
	    .compile("Warning: Label (\\d+) at \\((\\d+)\\) defined but not used");

    @Inject
    private Logger logger;

    @Inject
    private FileSystem fileSystem;

    private final EvaluatorIssuesStoreRemote designIssuesStore;

    public GNUFortranEvaluator() {
	super(ID, NAME, PLUGIN_VERSION, DESCRIPTION);
	designIssuesStore = JndiUtils.createRemoteEJBInstance(EvaluatorIssuesStoreRemote.class,
		EvaluatorIssuesStoreRemote.JNDI_NAME);
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameters() {
	return CONFIGURATION_PARAMETERS;
    }

    @Override
    public void setConfigurationParameter(ConfigurationParameter<?> parameter, Object value) {
	// Intentionally left empty
    }

    @Override
    public ConfigurationParameter<?>[] getConfigurationParameter(ConfigurationParameter<?> parameter) {
	return new ConfigurationParameter<?>[] {};
    }

    @Override
    public EvaluatorInformation getInformation() {
	return INFORMATION;
    }

    @Override
    public Parameter<?>[] getParameters() {
	return PARAMETERS;
    }

    @Override
    public QualityCharacteristic[] getQualityCharacteristics() {
	return CHARACTERISTICS;
    }

    @Override
    public void evaluate(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	AnalysisRunInformation information = analysisRun.getInformation();
	String projectId = information.getProjectId();
	long runId = information.getRunId();
	logger.info("Start evaluation for '" + projectId + "'/'" + runId + "...");
	String preAnalysisStdoutFile = HadoopClientHelper.getPreAnalysisScriptStdoutFile(projectId, runId);
	try (InputStream inputStream = fileSystem.open(new Path(preAnalysisStdoutFile));
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(inputStreamReader)) {
	    processPreAnalysisOutput(analysisRun, reader);
	    logger.info("Evaluation for '" + projectId + "'/'" + runId + " finished.");
	} catch (IllegalArgumentException | IOException | FileStoreException e) {
	    throw new EvaluationStoreException("Could not read the pre-analysis script output.", e);
	}
    }

    private void processPreAnalysisOutput(AnalysisRun analysisRun, BufferedReader reader)
	    throws IOException, FileStoreException, EvaluationStoreException {
	List<String> lineBuffer = new ArrayList<>();
	AnalysisFileTree fileTree = analysisRun.getFileTree();
	AnalysisFileTree currentFile = null;
	Map<String, List<Issue>> issues = new HashMap<>();
	String line;
	while ((line = reader.readLine()) != null) {
	    lineBuffer.add(line);
	    Matcher matcher = START_COMPILE_LINE_PATTERN.matcher(line);
	    if (matcher.find()) {
		saveFileIssues(analysisRun, currentFile, issues);
		lineBuffer.clear();
		issues.clear();
		currentFile = findNode(fileTree, matcher.group(1));
	    } else if ((line.contains("Warning:")) && (currentFile != null)) {
		processWarning(issues, analysisRun, currentFile, line, lineBuffer);
	    }
	}
	saveFileIssues(analysisRun, currentFile, issues);
    }

    private void saveFileIssues(AnalysisRun analysisRun, AnalysisFileTree currentFile, Map<String, List<Issue>> issues)
	    throws EvaluationStoreException {
	if (issues.size() > 0) {
	    CodeRangeIssues codeRangeIssues = new CodeRangeIssues(currentFile.getSourceCodeLocation(),
		    CodeRangeType.FILE, currentFile.getName(), PARAMETERS, issues);
	    FileIssuesImpl fileIssues = new FileIssuesImpl(ID, PLUGIN_VERSION, currentFile.getHashId(),
		    currentFile.getSourceCodeLocation(), new Date(), PARAMETERS);
	    fileIssues.addCodeRangeIssue(codeRangeIssues);
	    storeFileResults(analysisRun, createAnalysisInformation(currentFile), fileIssues);
	}
    }

    private AnalysisInformation createAnalysisInformation(AnalysisFileTree currentFile) {
	return new AnalysisInformation(currentFile.getHashId(), new Date(), 0, true, "Fortran", "95", ID,
		PLUGIN_VERSION);
    }

    private AnalysisFileTree findNode(AnalysisFileTree fileTree, String currentSource) {
	FindNodeVisitor visitor = new FindNodeVisitor(currentSource);
	TreeWalker.walk(visitor, fileTree);
	return visitor.getFoundNode();
    }

    private class FindNodeVisitor implements TreeVisitor<AnalysisFileTree> {

	private final String currentSource;
	private AnalysisFileTree foundNode = null;

	public FindNodeVisitor(String currentSource) {
	    super();
	    this.currentSource = currentSource;
	}

	@Override
	public WalkingAction visit(AnalysisFileTree node) {
	    String currentPath = node.getPathFile(false).getPath();
	    if ((currentSource.endsWith(currentPath)) && (node.isFile())) {
		foundNode = node;
		return WalkingAction.ABORT;
	    }
	    return WalkingAction.PROCEED;
	}

	public AnalysisFileTree getFoundNode() {
	    return foundNode;
	}
    }

    private static class IssueLocation {

	private static IssueLocation valueOf(List<String> lines) {
	    for (int i = lines.size() - 1; i >= 0; --i) {
		Matcher matcher = POSITION_LINE_PATTERN.matcher(lines.get(i));
		if (matcher.matches()) {
		    return new IssueLocation(Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
		}
	    }
	    return null;
	}

	private final int line;
	private final int column;

	public IssueLocation(int line, int column) {
	    super();
	    this.line = line;
	    this.column = column;
	}

	public int getLine() {
	    return line;
	}

	public int getColumn() {
	    return column;
	}

    }

    private void processWarning(Map<String, List<Issue>> issues, AnalysisRun analysisRun, AnalysisFileTree currentFile,
	    String line, List<String> lineBuffer) {
	logger.info("Found Warning for '" + currentFile.getPathFile(true) + "': " + line + "\nbuffer: " + lineBuffer);
	Issue issue = null;
	if (line.contains("Warning: Equality comparison for REAL")) {
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		issue = new Issue(Severity.CRITICAL, Classification.DEFECT, location.getLine(), location.getColumn(), 1,
			1, 1, REAL_EQUALITY_CHECK, "");
	    }
	} else if (line.contains("Warning: Inequality comparison for REAL")) {
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		issue = new Issue(Severity.CRITICAL, Classification.DEFECT, location.getLine(), location.getColumn(), 1,
			1, 1, REAL_INEQUALITY_CHECK, "");
	    }
	} else if (line.contains("Warning: Nonconforming tab character in column")) {
	    Matcher matcher = NON_CONFORMING_TAB_PATTERN.matcher(line);
	    if (matcher.find()) {
		int lineNum = Integer.parseInt(matcher.group(1));
		int columnNum = Integer.parseInt(matcher.group(2));
		issue = new Issue(Severity.MAJOR, Classification.STYLE_ISSUE, lineNum, columnNum, 1, 1, 1,
			NONCONFORMING_FIXED_FORM, "");
	    }
	} else if (line.contains("Warning: Nonconforming tab character at")) {
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		issue = new Issue(Severity.MINOR, Classification.STYLE_ISSUE, location.getLine(), location.getColumn(),
			1, 1, 1, NONCONFORMING_TAB_CHARACTER, "");
	    }
	} else if (line.contains("Warning: Obsolescent feature: Old-style character length")) {
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		issue = new Issue(Severity.MAJOR, Classification.STYLE_ISSUE, location.getLine(), location.getColumn(),
			1, 1, 1, OBSOLESCENT_CHARACTER_LENGTH, "");
	    }
	} else if (line.contains(
		"Warning: Nonexistent include directory ‘/home/ludwig/git/dyn3d/DYN3D2GNeutronKinetics/aggregation/fort/DYN3D2G’")) {
	    // we cannot do anything here...
	} else if (line.contains("Warning: Unused dummy argument")) {
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		Matcher matcher = UNUSED_DUMMY_ARGUMENT_PATTERN.matcher(line);
		if (matcher.find()) {
		    String parameterName = matcher.group(1);
		    issue = new Issue(Severity.MAJOR, Classification.DESIGN_ISSUE, location.getLine(),
			    location.getColumn(), 1, 1, 1, UNUSED_DUMMY_PARAMETER,
			    "Parameter '" + parameterName + "' is declared, but not used.");
		}
	    }
	} else if (line.contains("Warning: Unused variable")) {
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		Matcher matcher = UNUSED_VARIABLE_PATTERN.matcher(line);
		if (matcher.find()) {
		    String variableName = matcher.group(1);
		    issue = new Issue(Severity.CRITICAL, Classification.DEFECT, location.getLine(),
			    location.getColumn(), 1, 1, 1, UNUSED_VARIABLE,
			    "Variable '" + variableName + "' is declared, but not used.");
		}
	    }
	} else if (line.contains("Warning: Label ") && line.contains(" defined but not used")) {
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		Matcher matcher = UNUSED_LABEL_PATTERN.matcher(line);
		if (matcher.find()) {
		    String labelName = matcher.group(1);
		    issue = new Issue(Severity.MAJOR, Classification.DEFECT, location.getLine(), location.getColumn(),
			    1, 1, 1, UNUSED_LABEL, "Label '" + labelName + "' was defined, but is not used.");
		}
	    }
	} else if (line.contains("Warning: Unused module variable")) { // [-Wunused-variable]
	    IssueLocation location = IssueLocation.valueOf(lineBuffer);
	    if (location != null) {
		Matcher matcher = UNUSED_MODULE_VARIABLE_PATTERN.matcher(line);
		if (matcher.find()) {
		    String variableName = matcher.group(1);
		    issue = new Issue(Severity.CRITICAL, Classification.DEFECT, location.getLine(),
			    location.getColumn(), 1, 1, 1, UNUSED_MODULE_VARIABLE,
			    "Variable '" + variableName + "' is not used.");
		}
	    }
	}
	if (issue != null) {
	    String parameterName = issue.getParameter().getName();
	    List<Issue> list = issues.get(parameterName);
	    if (list == null) {
		list = new ArrayList<>();
		issues.put(parameterName, list);
	    }
	    list.add(issue);
	} else {
	    logger.warn("Found unknown warning for '" + currentFile.getPathFile(true) + "': " + line + "\nbuffer: "
		    + lineBuffer);
	}
    }

    @Override
    protected FileIssues processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected DirectoryIssues processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected DirectoryIssues processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected FileIssues readFileResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun, AnalysisInformation analysisInformation,
	    FileIssuesImpl results) throws EvaluationStoreException {
	designIssuesStore.storeFileResults(analysisRun, analysisInformation, results);
    }

    @Override
    protected DirectoryIssues readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    protected void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    DirectoryIssuesImpl results) throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    protected ProjectIssues readProjectResults(String projectId, long runId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected boolean hasProjectResults(String projectId, long runId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    protected void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    ProjectIssuesImpl results) throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

}
