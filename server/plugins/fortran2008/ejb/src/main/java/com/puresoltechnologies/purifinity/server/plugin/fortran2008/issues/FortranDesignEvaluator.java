package com.puresoltechnologies.purifinity.server.plugin.fortran2008.issues;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;

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
import com.puresoltechnologies.purifinity.evaluation.domain.Classification;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.CodeRangeIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.DirectoryIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.FileIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.Issue;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.IssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.ProjectIssuesImpl;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.AbstractIssueEvaluator;
import com.puresoltechnologies.trees.TreeException;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Evaluator.class)
public class FortranDesignEvaluator extends AbstractIssueEvaluator {

    public static final String ID = FortranDesignEvaluator.class.getName();
    public static final String NAME = "Fortran Design Evaluator";
    public static final Version PLUGIN_VERSION = new Version(1, 0, 0);
    public static final String DESCRIPTION = "This evaluator checks commong design weaknesses in Fortran code.";
    public static final QualityCharacteristic[] CHARACTERISTICS = new QualityCharacteristic[] {};
    public static final ConfigurationParameter<?>[] CONFIGURATION_PARAMETERS = new ConfigurationParameter[] {};
    public static final MetricParameter<?>[] PARAMETERS = new MetricParameter[] {};
    public static final Set<String> DEPENDENCIES = new HashSet<>();

    @Inject
    private Logger logger;

    private final IssueParameter USAGE_OF_IMPLICIT = new IssueParameter("UsageOfImplicit", "",
	    "The usage of 'IMPLICIT' statement should be avoided.");;
    private final IssueParameter NO_IMPLICIT_NONE = new IssueParameter("NoImplicitNone", "",
	    "No 'IMPLICIT NONE' was found.");
    private final IssueParameter COMBINED_USAGE_OF_IMPLICIT = new IssueParameter("CombinedUsageOfImplicit", "",
	    "Combined usage of 'IMPLICIT NONE' and 'IMPLICIT' was found.");

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
    protected FileIssues readFileResults(HashId hashId) throws EvaluationStoreException {
	return getIssuesStore().readFileResults(hashId, FortranDesignEvaluator.ID);
    }

    @Override
    protected boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	return getIssuesStore().hasFileResults(hashId, FortranDesignEvaluator.ID);
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun, CodeAnalysis fileAnalysis, FileIssuesImpl issues)
	    throws EvaluationStoreException {
	getIssuesStore().storeFileResults(analysisRun, fileAnalysis, issues);
    }

    @Override
    protected DirectoryIssues readDirectoryResults(HashId hashId) throws EvaluationStoreException {
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
	    DirectoryIssuesImpl metrics) throws EvaluationStoreException {
	// intentionally left empty, because directory storage is not supported
    }

    @Override
    protected ProjectIssues readProjectResults(String projectId, long runId) throws EvaluationStoreException {
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
	    ProjectIssuesImpl metrics) throws EvaluationStoreException {
	// intentionally left empty, because project storage is not supported
    }

    @Override
    protected DirectoryIssues processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	// intentionally left empty
	return null;
    }

    @Override
    protected FileIssues processFile(AnalysisRun analysisRun, CodeAnalysis analysis)
	    throws InterruptedException, UniversalSyntaxTreeEvaluationException, EvaluationStoreException {
	HashId hashId = analysis.getAnalysisInformation().getHashId();
	List<IssueParameter> parameters = new ArrayList<>();
	parameters.add(NO_IMPLICIT_NONE);
	parameters.add(USAGE_OF_IMPLICIT);
	parameters.add(COMBINED_USAGE_OF_IMPLICIT);
	SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
	FileIssuesImpl fileIssues = new FileIssuesImpl(FortranDesignEvaluator.ID, FortranDesignEvaluator.PLUGIN_VERSION,
		hashId, sourceCodeLocation, new Date(), parameters.toArray(new IssueParameter[parameters.size()]));
	for (CodeRange codeRange : analysis.getAnalyzableCodeRanges()) {
	    if ((codeRange.getType() == CodeRangeType.MODULE) || (codeRange.getType() == CodeRangeType.FILE)
		    || (codeRange.getType() == CodeRangeType.DIRECTORY)) {
		continue;
	    }
	    Map<String, List<Issue>> implicitIssues = checkForImplicitIssues(analysisRun, analysis, codeRange);
	    CodeRangeIssues issues = new CodeRangeIssues(fileIssues.getSourceCodeLocation(), codeRange.getType(),
		    codeRange.getCanonicalName(), parameters.toArray(new IssueParameter[parameters.size()]),
		    implicitIssues);
	    fileIssues.addCodeRangeDesignIssue(issues);
	}
	return fileIssues;
    }

    private Map<String, List<Issue>> checkForImplicitIssues(AnalysisRun analysisRun, CodeAnalysis fileAnalysis,
	    CodeRange codeRange) throws EvaluationStoreException {
	try {
	    UniversalSyntaxTree ust = codeRange.getUST();
	    UniversalSyntaxTree specificationPart;
	    switch (codeRange.getType()) {
	    case PROGRAM:
		if (!"main-program".equals(ust.getName())) {
		    logger.error("Program statement '" + ust.getName() + "' not expected.");
		}
		specificationPart = ust.getChild("specification-part");
		break;
	    case SUBROUTINE:
		if (!"subroutine-subprogram".equals(ust.getName())) {
		    logger.error("Subroutine statement '" + ust.getName() + "' not expected.");
		}
		specificationPart = ust.getChild("specification-part");
		break;
	    case FUNCTION:
		if (!"function-subprogram".equals(ust.getName())) {
		    logger.error("Function statement '" + ust.getName() + "' not expected.");
		}
		specificationPart = ust.getChild("specification-part");
		if (specificationPart == null) {
		    logger.error("A 'function-subroutine' needs to have a 'specification-part'.");
		}
		break;
	    case MODULE:
		if ((!"module".equals(ust.getName())) && (!"submodule".equals(ust.getName()))) {
		    logger.error("Module statement '" + ust.getName() + "' not expected.");
		}
		specificationPart = ust.getChild("specification-part");
		if (("module".equals(ust.getName())) && (specificationPart == null)) {
		    logger.error("A 'module' needs to have a 'specification-part'.");
		}
		break;
	    case INTERFACE:
		if (!"interface-body".equals(ust.getName())) {
		    logger.error("Interface statement '" + ust.getName() + "' not expected.");
		}
		specificationPart = ust.getChild("specification-part");
		if (("module".equals(ust.getName())) && (specificationPart == null)) {
		    logger.error("A 'module' needs to have a 'specification-part'.");
		}
		break;
	    case BLOCK:
		if (!"block-data".equals(ust.getName())) {
		    logger.error("Block statement '" + ust.getName() + "' not expected.");
		}
		specificationPart = ust.getChild("block-data-stmt");
		break;
	    default:
		specificationPart = null;
	    }
	    if (specificationPart != null) {
		return check(specificationPart);
	    } else {
		return new HashMap<>();
	    }
	} catch (TreeException e) {
	    logger.error("Could not check tree for '" + codeRange + "'.", e);
	    return new HashMap<>();
	}
    }

    public Map<String, List<Issue>> check(UniversalSyntaxTree specificationPart) {
	Map<String, List<Issue>> issues = new HashMap<>();
	boolean hasImplicitNone = false;
	boolean hasImplicit = false;
	int implicitCount = 0;
	List<UniversalSyntaxTree> implicitPartStatements = specificationPart.getChildren("implicit-part-stmt");
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
			List<Issue> issueList = issues.get(USAGE_OF_IMPLICIT.getName());
			if (issueList == null) {
			    issueList = new ArrayList<>();
			    issues.put(USAGE_OF_IMPLICIT.getName(), issueList);
			}
			UniversalSyntaxTreeMetaData metaData = implicitStatement.getMetaData();
			issueList.add(new Issue(Severity.MAJOR, Classification.DESIGN_ISSUE, metaData.getLine(),
				metaData.getColumn(), metaData.getLineNum(), metaData.getLength(), 1,
				USAGE_OF_IMPLICIT));
			break;
		    }
		}
	    }
	}
	if (implicitCount == 0) {
	    List<Issue> issueList = issues.get(NO_IMPLICIT_NONE.getName());
	    if (issueList == null) {
		issueList = new ArrayList<>();
		issues.put(NO_IMPLICIT_NONE.getName(), issueList);
	    }
	    UniversalSyntaxTreeMetaData metaData = specificationPart.getMetaData();
	    issueList.add(new Issue(Severity.MAJOR, Classification.DESIGN_ISSUE, metaData.getLine(),
		    metaData.getColumn(), metaData.getLineNum(), metaData.getLength(), 1, NO_IMPLICIT_NONE));
	} else if (hasImplicit && hasImplicitNone) {
	    List<Issue> issueList = issues.get(COMBINED_USAGE_OF_IMPLICIT.getName());
	    if (issueList == null) {
		issueList = new ArrayList<>();
		issues.put(COMBINED_USAGE_OF_IMPLICIT.getName(), issueList);
	    }
	    UniversalSyntaxTreeMetaData metaData = specificationPart.getMetaData();
	    issueList.add(new Issue(Severity.CRITICAL, Classification.DESIGN_ISSUE, metaData.getLine(),
		    metaData.getColumn(), metaData.getLineNum(), metaData.getLength(), 1, COMBINED_USAGE_OF_IMPLICIT));
	}
	return issues;
    }

    @Override
    protected DirectoryIssues processDirectory(AnalysisRun analysisRun, AnalysisFileTree directory)
	    throws InterruptedException, EvaluationStoreException {
	// intentionally left empty; designs cannot be evaluated on directories
	return null;
    }

}
