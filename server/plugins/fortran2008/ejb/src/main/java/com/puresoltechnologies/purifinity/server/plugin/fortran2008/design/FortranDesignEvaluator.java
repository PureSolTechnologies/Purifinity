package com.puresoltechnologies.purifinity.server.plugin.fortran2008.design;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.puresoltechnologies.commons.domain.ConfigurationParameter;
import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.ust.eval.UniversalSyntaxTreeEvaluationException;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.domain.AnalysisFileTree;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.DirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.FileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericDirectoryMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericFileMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.GenericProjectMetrics;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.MetricParameter;
import com.puresoltechnologies.purifinity.evaluation.domain.metrics.ProjectMetrics;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.design.AbstractDesignEvaluator;
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
    protected FileMetrics readFileResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected boolean hasFileResults(HashId hashId) throws EvaluationStoreException {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    protected void storeFileResults(AnalysisRun analysisRun, CodeAnalysis fileAnalysis, GenericFileMetrics metrics)
	    throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    protected DirectoryMetrics readDirectoryResults(HashId hashId) throws EvaluationStoreException {
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
	    GenericDirectoryMetrics metrics) throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    protected ProjectMetrics readProjectResults(String projectId, long runId) throws EvaluationStoreException {
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
	    GenericProjectMetrics metrics) throws EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    protected DirectoryMetrics processProject(AnalysisRun analysisRun, boolean enableReevaluation)
	    throws InterruptedException, EvaluationStoreException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    protected void processAsFile(AnalysisRun analysisRun, AnalysisFileTree fileNode, boolean enableReevaluation)
	    throws FileStoreException, InterruptedException, UniversalSyntaxTreeEvaluationException,
	    EvaluationStoreException {
	// TODO Auto-generated method stub

    }

    @Override
    protected void processAsDirectory(AnalysisRun analysisRun, AnalysisFileTree directoryNode,
	    boolean enableReevaluation) throws FileStoreException, InterruptedException,
		    UniversalSyntaxTreeEvaluationException, DirectoryStoreException, EvaluationStoreException {
	// TODO Auto-generated method stub

    }

}
