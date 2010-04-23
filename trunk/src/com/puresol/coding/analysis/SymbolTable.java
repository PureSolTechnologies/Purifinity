package com.puresol.coding.analysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.exceptions.StrangeSituationException;

public class SymbolTable implements Serializable {

    private static final long serialVersionUID = -5965509627847174126L;

    private final List<Symbol> symbols = new ArrayList<Symbol>();

    public SymbolTable() {
    }

    public void add(Symbol symbol) {
	symbols.add(symbol);
    }

    public List<Symbol> getSymbols() {
	return symbols;
    }

    @Override
    public Object clone() {
	try {
	    SymbolTable cloned = (SymbolTable) super.clone();
	    for (Symbol symbol : this.symbols) {
		cloned.symbols.add((Symbol) symbol.clone());
	    }
	    return cloned;
	} catch (CloneNotSupportedException e) {
	    throw new StrangeSituationException(e);
	}
    }
}
