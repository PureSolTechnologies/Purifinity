package com.puresoltechnologies.parsers.api.source;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;

/**
 * This is a central interface for storing the location of source code in
 * {@link SourceCode}, {@link Token} and other places where the source is needed
 * for tracking and user feedback to locate issues.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface CodeLocation extends Serializable {

	/**
	 * This method returns the name of the returned source code. This might be a
	 * file name or another name of a compilation unit or piece of source code.
	 * 
	 * @return Returns a name of the location.
	 */
	public String getName();

	/**
	 * This method returns a logical internal location which can be used to
	 * group the different source code pieces for statistics and UI. A natural
	 * location might be the relative path of a file system within the project
	 * folder. It also be a path within a SCM code repository or something
	 * similar.
	 * 
	 * @return Returns a logicatl
	 */
	public String getInternalLocation();

	/**
	 * This method generates a human readable string where the source comes
	 * from. For {@link File} and {@link URL}, the normal toString()
	 * implementations might be sufficient. For {@link DatabaseCodeLocation} for
	 * example, the string might be something else. This String is generated
	 * here.
	 * 
	 * @return A {@link String} is returned telling the original location in
	 *         human readable way.
	 */
	public String getHumanReadableLocationString();

	/**
	 * This method opens an input stream to the source file. This stream can be
	 * used for generic processing.
	 * <p>
	 * <b>Attention(!):</b> The caller is responsible for closing the stream!
	 * 
	 * @return An {@link InputStream} object is returned.
	 * @throws IOException
	 *             is thrown in any case of IO issues. For loading the source
	 *             code from the original location a IO process is needed some
	 *             how. Any issue should be translated into an
	 *             {@link IOException}.
	 */
	public InputStream openStream() throws IOException;

	/**
	 * This method loads the source code from the source specified in the object
	 * declaring this interface.
	 * 
	 * @return A {@link SourceCode} object is returned.
	 * @throws IOException
	 *             is thrown in any case of IO issues. For loading the source
	 *             code from the original location a IO process is needed some
	 *             how. Any issue should be translated into an
	 *             {@link IOException}.
	 */
	public SourceCode loadSourceCode() throws IOException;

	/**
	 * This method returns a new {@link CodeLocation} which is pointing to a new
	 * location specified by a relative path/location provided as parameter.
	 * 
	 * This is used for preprocessor #include actions.
	 * 
	 * @param relativePath
	 * @return
	 */
	public CodeLocation newRelativeSource(String relativePath);

	/**
	 * This method checks whether this source is available or not. This is
	 * needed to check for the presence of some specified sources like include
	 * files.
	 * 
	 * @return True is returned if the source is available. False is returned
	 *         otherwise.
	 */
	public boolean isAvailable();
}
