package com.puresoltechnologies.parsers.api.source;

import java.io.Serializable;
import java.util.List;

import com.puresoltechnologies.commons.HashId;

/**
 * This class represents a whole source code from a file and additional later
 * changes through a preprocessor.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface SourceCode extends Serializable, Cloneable {

	public List<SourceCodeLine> getLines();

	public HashId getHashId();

}
