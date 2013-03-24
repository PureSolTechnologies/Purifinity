package com.puresol.coding.evaluation.api;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.puresol.utils.math.Parameter;
import com.puresol.utils.math.Value;

/**
 * @author Rick-Rainer Ludwig
 */
public interface MetricResults extends Serializable {

	/**
	 * This method returns a {@link List} of {@link Parameter} which contains
	 * the definitions of all parameters which are returned by
	 * {@link #getValues()}.
	 * 
	 * @return A {@link List} of {@link Parameter} is returned containing the
	 *         definitions.
	 */
	public List<Parameter<?>> getParameters();

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
	public List<Map<String, Value<?>>> getValues();

}
