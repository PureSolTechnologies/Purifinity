package com.puresol.coding.langelements;

import java.util.List;

/**
 * This interface is for a language independent class declaration.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface ClassLanguageElement {

	public String getName();

	public List<VariableLanguageElement> getFields();

}
