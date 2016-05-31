package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTree;
import com.puresoltechnologies.parsers.ust.UniversalSyntaxTreeMetaData;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRange;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssue;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.FileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.CodeRangeDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DirectoryDesignIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.design.FileDesignIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.design.ProjectDesignIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.design.ProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.design.AbstractDesignEvaluator;
import com.puresoltechnologies.trees.TreeVisitor;
import com.puresoltechnologies.trees.TreeWalker;
import com.puresoltechnologies.trees.WalkingAction;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Evaluator.class)
public class FortranDesignEvaluator extends AbstractDesignEvaluator {

    public static final String ID = FortranDesignEvaluator.class.getName();
    public static final String NAME = "Fortran Design Evaluator";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
    public static final String DESCRIPTION = "This evaluator checks commong design weaknesses in Fortran code.";
    public static final QualityCharacteristic[] CHARACTERISTICS = new QualityCharacteristic[] {};
    public static final ConfigurationParameter<?>[] CONFIGURATION_PARAMETERS = new ConfigurationParameter[] {};
    public static final MetricParameter<?>[] PARAMETERS = new MetricParameter[] {};
    public static final Set<String> DEPENDENCIES = new HashSet<>();

    private final DesignIssueParameter USAGE_OF_IMPLICIT = new DesignIssueParameter("UsageOfImplicit", "",
	    "The usage of 'IMPLICIT' statement should be avoided.");;
    private final DesignIssueParameter NO_IMPLICIT_NONE = new DesignIssueParameter("NoImplicitNone", "",
	    "No 'IMPLICIT NONE' was found.");
    private final DesignIssueParameter COMBINED_USAGE_OF_IMPLICIT = new DesignIssueParameter("CombinedUsageOfImplicit",
	    "", "Combined usage of 'IMPLICIT NONE' and 'IMPLICIT' was found.");

    public FortranDesignEvaluator() {
	super(ID, NAME, PLUGIN_VERSION, DESCRIPTION);
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
    protected FileDesignIssues readFileResults(HashId hashId) throws EvaluationStoreException {
	return getDesignIssuessStore().readFileResults(hashId, FortranDesignEvaluator.ID);
    }

    @Override
    protected boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	return getDesignIssuessStore().hasFileResults(hashId, FortranDesignEvaluator.ID);
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun, CodeAnalysis fileAnalysis, FileDesignIssuesImpl issues)
	    throws EvaluationStoreException {
	getDesignIssuessStore().storeFileResults(analysisRun, fileAnalysis, issues);
    }

    @Override
    protected DirectoryDesignIssues readDirectoryResults(HashId hashId) throws EvaluationStoreException {
	// intentionally left empty, because directory storage is not supported
	return null;
    }

    @Override
    protected boolean hasDirectoryResults(HashId hashId) throws EvaluationStoreException {
	// intentionally left empty, because directory storage is not supported
	return false;
    }

    @Override
    protected void storeDirectoryResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    DirectoryDesignIssuesImpl metrics) throws EvaluationStoreException {
	// intentionally left empty, because directory storage is not supported
    }

    @Override
    protected ProjectDesignIssues readProjectResults(String projectId, long runId) throws EvaluationStoreException {
	// intentionally left empty, because project storage is not supported
	return null;
    }

    @Override
    protected boolean hasProjectResults(String projectId, long runId) throws EvaluationStoreException {
	// intentionally left empty, because project storage is not supported
	return false;
    }

    @Override
    protected void storeProjectResults(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    ProjectDesignIssuesImpl metrics) throws EvaluationStoreException {
	// intentionally left empty, because project storage is not supported
    }

    @Override
    protected DirectoryDesignIssues processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	// intentionally left empty
	return null;
    }

    @Override
    protected FileDesignIssues processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	List<DesignIssueParameter> parameters = new ArrayList<>();
	parameters.add(NO_IMPLICIT_NONE);
	parameters.add(USAGE_OF_IMPLICIT);
	parameters.add(COMBINED_USAGE_OF_IMPLICIT);
	SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
	FileDesignIssuesImpl fileIssues = new FileDesignIssuesImpl(FortranDesignEvaluator.ID,
		FortranDesignEvaluator.PLUGIN_VERSION, hashId, sourceCodeLocation, new Date(),
		parameters.toArray(new DesignIssueParameter[parameters.size()]));
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    if ((codeRange.getType() == CodeRangeType.MODULE) || (codeRange.getType() == CodeRangeType.FILE)
		    || (codeRange.getType() == CodeRangeType.DIRECTORY)) {
		continue;
	    }
	    Map<String, List<DesignIssue>> implicitIssues = checkForImplicitIssues(analysisRun, analysis, codeRange);
	    CodeRangeDesignIssues issues = new CodeRangeDesignIssues(fileIssues.getSourceCodeLocation(),
		    codeRange.getType(), codeRange.getCanonicalName(),
		    parameters.toArray(new DesignIssueParameter[parameters.size()]), implicitIssues);
	    fileIssues.addCodeRangeDesignIssue(issues);
	}
	return fileIssues;
    }

    private Map<String, List<DesignIssue>> checkForImplicitIssues(AnalysisRun analysisRun, CodeAnalysis fileAnalysis,
	    CodeRange codeRange) throws EvaluationStoreException {
	ImplicitVisitor visitor = new ImplicitVisitor();
	TreeWalker.walk(visitor, codeRange.getUST());
	return visitor.getIssues();
    }

    private class ImplicitVisitor implements TreeVisitor<UniversalSyntaxTree> {

	private final Map<String, List<DesignIssue>> issues = new HashMap<>();

	@Override
	public WalkingAction visit(UniversalSyntaxTree node) {
	    if (!"specification-part".equals(node.getName())) {
		return WalkingAction.PROCEED;
	    }
	    boolean hasImplicitNone = false;
	    boolean hasImplicit = false;
	    int implicitCount = 0;
	    List<UniversalSyntaxTree> implicitPartStatements = node.getChildren("implicit-part-stmt");
	    for (UniversalSyntaxTree implicitPartStatement : implicitPartStatements) {
		List<UniversalSyntaxTree> implicitStatements = implicitPartStatement.getChildren("implicit-stmt");
		for (UniversalSyntaxTree implicitStatement : implicitStatements) {
		    ++implicitCount;
		    for (UniversalSyntaxTree implicitStamentChild : implicitStatement.getChildren()) {
			if ("NONE".equalsIgnoreCase(implicitStamentChild.getContent())) {
			    hasImplicitNone = true;
			    break;
			} else if ("implicit-spec-list".equals(implicitStamentChild.getName())) {
			    hasImplicit = true;
			    List<DesignIssue> issueList = issues.get(USAGE_OF_IMPLICIT.getName());
			    if (issueList == null) {
				issueList = new ArrayList<>();
				issues.put(USAGE_OF_IMPLICIT.getName(), issueList);
			    }
			    UniversalSyntaxTreeMetaData metaData = implicitStatement.getMetaData();
			    issueList.add(new DesignIssue(metaData.getLine(), metaData.getColumn(),
				    metaData.getLineNum(), metaData.getLength(), 1, USAGE_OF_IMPLICIT));
			    break;
			}
		    }
		}
	    }
	    if (implicitCount == 0) {
		List<DesignIssue> issueList = issues.get(NO_IMPLICIT_NONE.getName());
		if (issueList == null) {
		    issueList = new ArrayList<>();
		    issues.put(NO_IMPLICIT_NONE.getName(), issueList);
		}
		UniversalSyntaxTreeMetaData metaData = node.getMetaData();
		issueList.add(new DesignIssue(metaData.getLine(), metaData.getColumn(), metaData.getLineNum(),
			metaData.getLength(), 1, NO_IMPLICIT_NONE));
	    } else if (hasImplicit && hasImplicitNone) {
		List<DesignIssue> issueList = issues.get(COMBINED_USAGE_OF_IMPLICIT.getName());
		if (issueList == null) {
		    issueList = new ArrayList<>();
		    issues.put(COMBINED_USAGE_OF_IMPLICIT.getName(), issueList);
		}
		UniversalSyntaxTreeMetaData metaData = node.getMetaData();
		issueList.add(new DesignIssue(metaData.getLine(), metaData.getColumn(), metaData.getLineNum(),
			metaData.getLength(), 1, COMBINED_USAGE_OF_IMPLICIT));
	    }
	    return WalkingAction.LEAVE_BRANCH;
	}

	public Map<String, List<DesignIssue>> getIssues() {
	    return issues;
	}
    }

    @Override
    protected DirectoryDesignIssues processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	// intentionally left empty; designs cannot be evaluated on directories
	return null;
    }

}
