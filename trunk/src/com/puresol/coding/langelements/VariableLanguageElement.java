package com.puresol.coding.langelements;

import java.util.List;

/**
 * This is an interface for a language independent field in a class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface VariableLanguageElement {

    public String getName();

    public List<String> getModifiers();
    
    public String getVariableType();
}
