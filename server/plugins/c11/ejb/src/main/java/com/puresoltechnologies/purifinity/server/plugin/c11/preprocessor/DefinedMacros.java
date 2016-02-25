package com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.purifinity.server.plugin.c11.preprocessor.internal.Macro;

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
     *            is the name of the macro to be defined.
     */
    public void define(String name) {
	macros.put(name, new Macro(name));
    }

    /**
     * This method adds a new object-like macro to the definitions.
     * 
     * @param name
     *            is the name of the macro.
     * @param replacement
     *            is the {@link TokenStream} to be used as replacement.
     */
    public void define(String name, TokenStream replacement) {
	macros.put(name, new Macro(name, replacement));
    }

    /**
     * This method adds a new object-like macro to the definitions.
     * 
     * @param macro
     *            is the {@link Macro} to define/register.
     */
    public void define(Macro macro) {
	macros.put(macro.getName(), macro);
    }

    /**
     * This method checks for the presence of a macro.
     * 
     * @param name
     *            is the name of the macro.
     * @return <code>true</code> is returned in case the macro is defined.
     *         <code>false</code> is returned otherwise.
     */
    public boolean isDefined(String name) {
	return macros.containsKey(name);
    }

    /**
     * This method removes the definition of a macro.
     * 
     * @param name
     *            is the name of the macro to be removed.
     * @return <code>true</code> is returned if a macro was deleted.
     *         <code>false</code> is returned if no macro with this name was
     *         defined.
     */
    public boolean undefine(String name) {
	return macros.remove(name) != null;
    }

    /**
     * This method returns the definition of a macro.
     * 
     * @param name
     *            is the name of the macro.
     * @return A {@link Macro} is returned.
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
