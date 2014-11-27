package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.math.Parameter;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.os.HashId;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

/**
 * This is the central interface for all directory metric results classes. The
 * main difference to {@link FileMetrics} is that this class does not support
 * different code ranges and {@link CodeRangeType}s.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface DirectoryMetrics extends Metrics {

	/**
	 * This method returns the {@link HashId} of the directory for which the
	 * values are for.
	 * 
	 * @return A {@link HashId} is returned containing the hash id.
	 */
	public HashId getHashId();

	/**
	 * <p>
	 * This method returns all available values. The return type is a complex
	 * {@link List} of {@link Map} which contains {@link Value} objects
	 * referenced by parameter names.
	 * </p>
	 * <p>
	 * The returns value can best be seen as table content. The {@link List} of
	 * {@link Parameter} returned by {@link #getParameters()} is the definition
	 * of the table header. The list returned here are the table rows. A table
	 * row consists of a {@link Map} which represents the columns identified by
	 * the key of {@link String} type and the {@link Value} to be put into the
	 * cell. The Key of the map is the name of the parameter defined in the
	 * {@link List} of {@link Parameter} from {@link #getParameters()}.
	 * </p>
	 * 
	 * @return The values of result are returned. A detailed description can be
	 *         found in the documentation of this method.
	 */
	public Map<String, MetricValue<?>> getValues();
}
