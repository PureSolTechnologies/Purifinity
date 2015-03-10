package com.puresoltechnologies.purifinity.analysis.api;

import com.puresoltechnologies.parsers.source.SourceCodeLocation;

/**
 * This interface is the central interface for a programming language
 * specification.
 * 
 * <b>Attention! Programming languages are singletons and therefore, it can not
 * be serialized!</b>
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ProgrammingLanguage { // do not Serialize!

    /**
     * This method returns the name of the programming language.
     * 
     * @return
     */
    public String getName();

    /**
     * This method returns the version of the programming language.
     * 
     * @return
     */
    public String getVersion();

    /**
     * This method specifies whether the programming language is suitable for a
     * specified file or not.
     * 
     * @return
     */
    public boolean isSuitable(SourceCodeLocation source);

    /**
     * This method returns the grammar of the programming language.
     * 
     * @return
     */
    public LanguageGrammar getGrammar();

}
