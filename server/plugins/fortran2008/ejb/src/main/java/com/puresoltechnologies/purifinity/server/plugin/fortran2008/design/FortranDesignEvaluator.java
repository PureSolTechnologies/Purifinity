package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisInformation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DesignIssueParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.design.DirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.FileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericDirectoryDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericFileDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.GenericProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.design.ProjectDesignIssues;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.design.AbstractDesignEvaluator;
import com.puresoltechnologies.purifinity.server.plugin.fortran2008.FortranPlugin;
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

    @Inject
    private Logger logger;

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
	// TODO Auto-generated method stub
    }

    @Override
    public Object getConfigurationParameter(ConfigurationParameter<?> parameter) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected FileDesignIssues readFileResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun, CodeAnalysis fileAnalysis, GenericFileDesignIssues metrics)
	    throws EvaluationStoreException {
	// TODO Auto-generated method stub

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
	    GenericDirectoryDesignIssues metrics) throws EvaluationStoreException {
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
	    GenericProjectDesignIssues metrics) throws EvaluationStoreException {
	// intentionally left empty, because project storage is not supported
    }

    @Override
    protected DirectoryDesignIssues processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected void processAsFile(AnalysisRun analysisRun, AnalysisFileTree fileNode, boolean enableReevaluation)
	    throws FileStoreException, InterruptedException, UniversalSyntaxTreeEvaluationException,
	    EvaluationStoreException {
	HashId hashId = fileNode.getHashId();
	CodeAnalysis analysis = getFileStore().loadAnalysis(hashId, FortranPlugin.INFORMATION.getId(),
		FortranPlugin.INFORMATION.getVersion());
	if (analysis == null) {
	    return;
	}
	UniversalSyntaxTree universalSyntaxTree = analysis.getUniversalSyntaxTree();
	checkForImplicitIssues(analysisRun, analysis, universalSyntaxTree);
    }

    private void checkForImplicitIssues(AnalysisRun analysisRun, CodeAnalysis fileAnalysis,
	    UniversalSyntaxTree universalSyntaxTree) {
	TreeWalker.walk(new ImplicitVisitor(analysisRun, fileAnalysis), universalSyntaxTree);
    }

    private class ImplicitVisitor implements TreeVisitor<UniversalSyntaxTree> {

	private final AnalysisRun analysisRun;
	private final CodeAnalysis fileAnalysis;

	public ImplicitVisitor(AnalysisRun analysisRun, CodeAnalysis fileAnalysis) {
	    super();
	    this.analysisRun = analysisRun;
	    this.fileAnalysis = fileAnalysis;
	}

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
			    break;
			}
		    }
		}
	    }
	    if (implicitCount == 0) {
		try {
		    AnalysisInformation analysisInformation = fileAnalysis.getAnalysisInformation();
		    HashId hashId = analysisInformation.getHashId();
		    SourceCodeLocation sourceCodeLocation = analysisRun.findTreeNode(hashId).getSourceCodeLocation();
		    List<DesignIssueParameter> parameters = new ArrayList<>();
		    GenericFileDesignIssues issue = new GenericFileDesignIssues(FortranDesignEvaluator.ID,
			    FortranDesignEvaluator.PLUGIN_VERSION, hashId, sourceCodeLocation, new Date(),
			    parameters.toArray(new DesignIssueParameter[parameters.size()]));
		    // issue.addCodeRangeDesignIssue(new
		    // ImplicitDesignIssue(hashId, sourceCodeLocation, new
		    // Date()));
		    // TODO add design issue for missing IMPLICIT NONE
		    storeFileResults(analysisRun, fileAnalysis, issue);
		} catch (EvaluationStoreException e) {
		    logger.error("Could not store implict issue.", e);
		}
	    } else if (hasImplicit) {
		if (hasImplicitNone) {
		    // TODO add design issue for used IMPLICIT and IMPLICIT NONE
		} else {
		    // TODO add design issue for used IMPLICIT
		}
	    }
	    return WalkingAction.LEAVE_BRANCH;
	}

    }

    @Override
    protected void processAsDirectory(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    boolean enableReevaluation) throws FileStoreException, InterruptedException,
		    UniversalSyntaxTreeEvaluationException, DirectoryStoreException, EvaluationStoreException {
	// intentionally left empty; designs cannot be evaluated on directories
    }

}
