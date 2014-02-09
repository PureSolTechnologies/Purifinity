package com.puresoltechnologies.purifinity.framework.store.api;

import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;

public class MetricsResultsIterator implements Iterable<MetricsResult>,
		Iterator<MetricsResult> {

	private Row next = null;

	private final ResultSet resultSet;

	public MetricsResultsIterator(ResultSet resultSet) {
		super();
		this.resultSet = resultSet;
	}

	@Override
	public boolean hasNext() {
		if (next == null) {
			next = resultSet.one();
		}
		return next != null;
	}

	@Override
	public MetricsResult next() {
		if (next == null) {
			next = resultSet.one();
		}
		if (next == null) {
			return null;
		}
		MetricsResult result = convert(next);
		next = null;
		return result;
	}

	private MetricsResult convert(Row next) {
		Date time = next.getDate("time");
		long duration = next.getLong("duration");
		UUID project = next.getUUID("analysis_project");
		UUID analysisRun = next.getUUID("analysis_run");
		HashId hashId = HashId.valueOf(next.getString("hashid"));
		String internalDirectory = next.getString("internal_directory");
		String fileName = next.getString("file_name");
		String sourceCodeLocation = next.getString("source_code_location");
		String languageName = next.getString("language_name");
		String languageVersion = next.getString("language_version");
		String evaluatorName = next.getString("evaluator_name");
		String codeRangeName = next.getString("code_range_name");
		CodeRangeType codeRangeType = CodeRangeType.valueOf(next
				.getString("code_range_type"));
		SourceCodeQuality quality = SourceCodeQuality.valueOf(next
				.getString("quality"));
		float qualityLevel = next.getFloat("quality_level");
		String name = next.getString("name");
		String unit = next.getString("unit");
		double metric = next.getDouble("metric");
		LevelOfMeasurement levelOfMeasurement = LevelOfMeasurement.valueOf(next
				.getString("level_of_measurement"));
		String description = next.getString("description");
		MetricsResult metricsResult = new MetricsResult(time, duration,
				project, analysisRun, hashId, internalDirectory, fileName,
				sourceCodeLocation, languageName, languageVersion,
				evaluatorName, codeRangeName, codeRangeType, quality,
				qualityLevel, name, unit, metric, levelOfMeasurement,
				description);
		return metricsResult;
	}

	@Override
	public void remove() {
		throw new IllegalStateException("Removal is not supported.");
	}

	@Override
	public Iterator<MetricsResult> iterator() {
		return this;
	}

}
