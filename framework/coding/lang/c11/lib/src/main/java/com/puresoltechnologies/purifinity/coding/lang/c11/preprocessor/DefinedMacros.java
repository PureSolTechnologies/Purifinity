package com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.purifinity.coding.lang.c11.preprocessor.internal.Macro;

/**
 * This class handles the macro definitions put into the {@link C11Preprocessor}
 * and defined during the processor runs. This class's object references can be
 * put into the processors and read back to promote changes in the definitions.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class DefinedMacros {

    private final Map<String, Macro> macros = new HashMap<String, Macro>();

    /**
     * This method adds a new object-like macro to the definitions.
     * 
     * This method just takes a macro name like a call "cpp -Dname". The macro
     * defined here is declared as "1" per default as the GNU c preprocessor
     * uses to do so.
     * 
     * @param name
     * @param declaration
     */
    public void define(String name) {
	macros.put(name, new Macro(name));
    }

    /**
     * This method adds a new object-like macro to the definitions.
     * 
     * @param name
     * @param declaration
     */
    public void define(String name, TokenStream replacement) {
	macros.put(name, new Macro(name, replacement));
    }

    /**
     * This method adds a new object-like macro to the definitions.
     * 
     * @param name
     * @param declaration
     */
    public void define(Macro macro) {
	macros.put(macro.getName(), macro);
    }

    /**
     * This method checks for the presence of a macro.
     * 
     * @param name
     * @return
     */
    public boolean isDefined(String name) {
	return macros.containsKey(name);
    }

    /**
     * This method removes the definition of a macro.
     * 
     * @param name
     * @return
     */
    public boolean undefine(String name) {
	return macros.remove(name) != null;
    }

    /**
     * This method returns the definition of a macro.
     * 
     * @param name
     * @return
     */
    public Macro getMacro(String name) {
	return macros.get(name);
    }

    public void reset() {
	macros.clear();
    }

    public Collection<Macro> getMacros() {
	return macros.values();
    }
}
