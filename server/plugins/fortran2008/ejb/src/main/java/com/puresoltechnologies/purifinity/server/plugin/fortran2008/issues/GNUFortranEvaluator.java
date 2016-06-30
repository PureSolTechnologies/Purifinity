package com.puresoltechnologies.purifinity.server.plugin.fortran2008.issues;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRun;
import com.puresoltechnologies.purifinity.analysis.api.AnalysisRunInformation;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluationStoreException;
import com.puresoltechnologies.purifinity.evaluation.api.Evaluator;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorInformation;
import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.api.iso9126.QualityCharacteristic;
import com.puresoltechnologies.purifinity.evaluation.api.issues.IssueEvaluator;
import com.puresoltechnologies.purifinity.evaluation.domain.issues.IssueParameter;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.issues.EvaluatorIssuesStoreRemote;
import com.puresoltechnologies.purifinity.server.database.hadoop.utils.HadoopClientHelper;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;
import com.puresoltechnologies.versioning.Version;

@Stateless
@Remote(Evaluator.class)
public class GNUFortranEvaluator implements IssueEvaluator {

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

    public static final IssueParameter[] PARAMETERS = new IssueParameter[] {};

    static final Pattern START_COMPILE_LINE_PATTERN = Pattern.compile("f95\\s.*-c\\s+(\\S+)");

    @Inject
    private Logger logger;

    @Inject
    private FileSystem fileSystem;

    private final EvaluatorIssuesStoreRemote designIssuesStore;

    public GNUFortranEvaluator() {
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
	    processPreAnalysisOutput(reader);
	    logger.info("Evaluation for '" + projectId + "'/'" + runId + " finished.");
	} catch (IllegalArgumentException | IOException e) {
	    throw new EvaluationStoreException("Could not read the pre-analysis script output.", e);
	}
    }

    private void processPreAnalysisOutput(BufferedReader reader) throws IOException {
	List<String> lineBuffer = new ArrayList<>();
	File currentSource = null;
	String line;
	while ((line = reader.readLine()) != null) {
	    lineBuffer.add(line);
	    Matcher matcher = START_COMPILE_LINE_PATTERN.matcher(line);
	    if (matcher.find()) {
		lineBuffer.clear();
		currentSource = new File(matcher.group(1));
	    } else if ((line.contains("Warning:")) && (currentSource != null)) {
		logger.info("Found Warning for '" + currentSource + "': " + line + "\nbuffer: " + lineBuffer);
		lineBuffer.clear();
	    }
	}
    }

}
