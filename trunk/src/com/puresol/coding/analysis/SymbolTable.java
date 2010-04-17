package com.puresol.coding.analysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
}
