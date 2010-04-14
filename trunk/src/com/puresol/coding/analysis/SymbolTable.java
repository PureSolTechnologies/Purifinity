package com.puresol.coding.analysis;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {

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
