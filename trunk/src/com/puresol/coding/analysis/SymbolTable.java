package com.puresol.coding.analysis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.exceptions.StrangeSituationException;

public class SymbolTable implements Cloneable, Serializable {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbols == null) ? 0 : symbols.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SymbolTable other = (SymbolTable) obj;
		if (symbols == null) {
			if (other.symbols != null)
				return false;
		} else if (!symbols.equals(other.symbols))
			return false;
		return true;
	}
}
