package com.puresol.uhura.source;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import com.puresol.uhura.lexer.Token;

/**
 * This is a central interface for storing the source of source code in
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
     * @return
     */
    public String getName();

    /**
     * This method returns a logical location which can be used to group the
     * different source code pieces for statistics and UI. A natural location
     * might be the relative path of a file system within the project folder. It
     * also be a path within a SCM code repository or something similar.
     * 
     * @return
     */
    public String getLocation();

    /**
     * This method generates a human readable string where the source comes
     * from. For {@link File} and {@link URL}, the normal toString()
     * implementations might be sufficient. For {@link DatabaseSource} for
     * example, the string might be something else. This String is generated
     * here.
     * 
     * @return A {@link String} is returned telling the original location in
     *         human readable way.
     */
    public String getHumanReadableLocationString();

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
    public SourceCode load() throws IOException;

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

}
