/***************************************************************************
 *
 *   Analyser.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.CodeRange;
import com.puresol.coding.lang.Language;

/**
 * This is a interface to a analyser. It's used to implement a language
 * independent way to access a single analyser.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface Analyser {

    /**
     * Returns the language of the file analysed.
     * 
     * @return The language is returned.
     */
    public Language getLanguage();

    /**
     * The file which was analysed is returned.
     * 
     * @return The file is returned.
     */
    public File getFile();

    /**
     * This method returns all code ranges analysed within the analysed
     * file.
     * 
     * @return
     */
    public ArrayList<CodeRange> getCodeRanges();
}
