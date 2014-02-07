package com.puresoltechnologies.purifinity.framework.store.api;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.api.MetricValue;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class MetricsResult implements Serializable {

	private static final long serialVersionUID = 4442477069070272593L;

	private final Date time;
	private final long duration;
	private final UUID project;
	private final UUID analysisRun;
	private final HashId hashId;
	private final String internalDirectory;
	private final String fileName;
	private final String sourceCodeLocation;
	private final String languageName;
	private final String languageVersion;
	private final String evaluatorName;
	private final String codeRangeName;
	private final CodeRangeType codeRangeType;
	private final SourceCodeQuality quality;
	private final float qualityLevel;
	private final MetricValue value;

	public MetricsResult(Date time, long duration, UUID project,
			UUID analysisRun, HashId hashId, String internalDirectory,
			String fileName, String sourceCodeLocation, String languageName,
			String languageVersion, String evaluatorName, String codeRangeName,
			CodeRangeType codeRangeType, SourceCodeQuality quality,
			float qualityLevel, String name, String unit, double metric,
			LevelOfMeasurement levelOfMeasurement, String description) {
		this(time, duration, project, analysisRun, hashId, internalDirectory,
				fileName, sourceCodeLocation, languageName, languageVersion,
				evaluatorName, codeRangeName, codeRangeType, quality,
				qualityLevel, new MetricValue(metric,
						new ParameterWithArbitraryUnit<>(name, unit,
								levelOfMeasurement, description, Double.class)));
	}

	public MetricsResult(Date time, long duration, UUID project,
			UUID analysisRun, HashId hashId, String internalDirectory,
			String fileName, String sourceCodeLocation, String languageName,
			String languageVersion, String evaluatorName, String codeRangeName,
			CodeRangeType codeRangeType, SourceCodeQuality quality,
			float qualityLevel, MetricValue value) {
		super();
		this.time = time;
		this.duration = duration;
		this.project = project;
		this.analysisRun = analysisRun;
		this.hashId = hashId;
		this.internalDirectory = internalDirectory;
		this.fileName = fileName;
		this.sourceCodeLocation = sourceCodeLocation;
		this.languageName = languageName;
		this.languageVersion = languageVersion;
		this.evaluatorName = evaluatorName;
		this.codeRangeName = codeRangeName;
		this.codeRangeType = codeRangeType;
		this.quality = quality;
		this.qualityLevel = qualityLevel;
		this.value = value;
	}

	public Date getTime() {
		return time;
	}

	public long getDuration() {
		return duration;
	}

	public UUID getProject() {
		return project;
	}

	public UUID getAnalysisRun() {
		return analysisRun;
	}

	public HashId getHashId() {
		return hashId;
	}

	public String getInternalDirectory() {
		return internalDirectory;
	}

	public String getFileName() {
		return fileName;
	}

	public String getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public String getLanguageName() {
		return languageName;
	}

	public String getLanguageVersion() {
		return languageVersion;
	}

	public String getEvaluatorName() {
		return evaluatorName;
	}

	public String getCodeRangeName() {
		return codeRangeName;
	}

	public CodeRangeType getCodeRangeType() {
		return codeRangeType;
	}

	public SourceCodeQuality getQuality() {
		return quality;
	}

	public float getQualityLevel() {
		return qualityLevel;
	}

	public MetricValue getValue() {
		return value;
	}
}
