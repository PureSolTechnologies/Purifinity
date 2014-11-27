package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;

public class GenericFileMetrics extends AbstractMetrics implements FileMetrics {

	private static final long serialVersionUID = -3838440751773878139L;

	private final Set<Parameter<?>> parameters = new LinkedHashSet<>();
	private final List<GenericCodeRangeMetrics> codeRanges = new ArrayList<>();

	private final HashId hashId;
	private final SourceCodeLocation sourceCodeLocation;

	public GenericFileMetrics(String evaluatorId, HashId hashId,
			SourceCodeLocation sourceCodeLocation, Date time,
			Set<Parameter<?>> parameters) {
		super(evaluatorId, time);
		this.hashId = hashId;
		this.sourceCodeLocation = sourceCodeLocation;
		this.parameters.addAll(parameters);
	}

	public GenericFileMetrics(String evaluatorId, HashId hashId,
			SourceCodeLocation sourceCodeLocation, Date time,
			Set<Parameter<?>> parameters,
			List<GenericCodeRangeMetrics> codeRanges) {
		super(evaluatorId, time);
		this.hashId = hashId;
		this.sourceCodeLocation = sourceCodeLocation;
		this.parameters.addAll(parameters);
		this.codeRanges.addAll(codeRanges);
	}

	@Override
	public HashId getHashId() {
		return hashId;
	}

	@Override
	public SourceCodeLocation getSourceCodeLocation() {
		return sourceCodeLocation;
	}

	public void addCodeRangeMetrics(GenericCodeRangeMetrics metrics) {
		codeRanges.add(metrics);
	}

	@Override
	public Set<Parameter<?>> getParameters() {
		return parameters;
	}

	@Override
	public List<GenericCodeRangeMetrics> getValues() {
		return codeRanges;
	}
}
