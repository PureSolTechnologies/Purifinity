package com.puresoltechnologies.purifinity.analysis.domain;

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
     * @return A {@link String} is returned containing the name of the language.
     */
    public String getName();

    /**
     * This method returns the version of the programming language.
     * 
     * @return A String with the version of the language is returned. Due to not
     *         all languages use semantic versioning, a plain string is returned
     *         here.
     */
    public String getVersion();

    /**
     * This method specifies whether the programming language is suitable for a
     * specified file or not.
     * 
     * @return <code>true</code> is returned in case the source code fits to
     *         this language. <code>false</code> is returned otherwise.
     */
    public boolean isSuitable(SourceCodeLocation source);

    /**
     * This method returns the grammar of the programming language.
     * 
     * @return A {@link LanguageGrammar} object is returned containing the
     *         grammar of the language.
     */
    public LanguageGrammar getGrammar();

}
