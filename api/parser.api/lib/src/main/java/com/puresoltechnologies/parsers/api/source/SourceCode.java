package com.puresoltechnologies.parsers.api.source;

import java.io.Serializable;
import java.util.List;

<<<<<<< HEAD
import com.puresoltechnologies.commons.HashId;
=======
import com.puresoltechnologies.commons.utils.HashId;
>>>>>>> 22bb20bf218d5d810e936dd668128ce7c35efbf9

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
