package com.puresoltechnologies.purifinity.evaluation.domain.metrics;

import java.util.List;
import java.util.Map;

import com.puresoltechnologies.commons.domain.Parameter;
import com.puresoltechnologies.commons.domain.Value;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.analysis.domain.CodeRangeType;

/**
 * This is the central interface for all file metric results classes. The main
 * difference to {@link DirectoryMetrics} is that this class does support
 * different code ranges and {@link CodeRangeType}s.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface FileMetrics extends Metrics {

	/**
	 * This method returns the {@link HashId} of the file for which the values
	 * are for.
	 * 
	 * @return A {@link HashId} is returned containing the hash id.
	 */
	public HashId getHashId();

	/**
	 * This method returns the source code location of the source for which the
	 * metrics are for.
	 * 
	 * @return A {@link SourceCodeLocation} is returned containing the
	 *         information about the location of the source.
	 */
	public SourceCodeLocation getSourceCodeLocation();

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
	public List<CodeRangeMetrics> getCodeRangeMetrics();

}
