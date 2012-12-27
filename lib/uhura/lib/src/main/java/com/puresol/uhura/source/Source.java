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
public interface Source extends Serializable {

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
     * This method returns a new {@link Source} which is pointing to a new
     * location specified by a relative path/location provided as parameter.
     * 
     * This is used for preprocessor #include actions.
     * 
     * @param relativePath
     * @return
     */
    public Source newRelativeSource(String relativePath);

}
