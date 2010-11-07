package com.puresol.uhura.grammar.production;

/**
 * THIS CLASS IS NOT THREAD SAFE!!!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractConstruction implements Construction {

	private static final long serialVersionUID = -8190479779174841005L;

	private final String name;
	private final boolean isTerminal;
	private final int hashCode;

	public AbstractConstruction(String name, boolean isTerminal) {
		super();
		this.name = name;
		this.isTerminal = isTerminal;
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (isTerminal ? 1 : 0);
		hashCode = result;
	}

	/**
	 * @return the typeId
	 */
	public String getName() {
		return name;
	}

	@Override
	public boolean isTerminal() {
		return isTerminal;
	}

	@Override
	public boolean isNonTerminal() {
		return !isTerminal;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#toString()
	 */
	@Override
	public String toString() {
		if (isTerminal) {
			return name + ": (TERMINAL)";
		} else {
			return name + ": (NON-TERMINAL)";
		}
	}

	public String toShortString() {
		return name;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractConstruction other = (AbstractConstruction) obj;
		if (this.hashCode != other.hashCode) {
			return false;
		}
		if (isTerminal != other.isTerminal)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int compareTo(Construction other) {
		if (other == null) {
			return 1;
		}
		int result = this.getName().compareTo(other.getName());
		if (result != 0) {
			return result;
		}
		return Boolean.valueOf(isTerminal()).compareTo(
				Boolean.valueOf(other.isTerminal()));
	}
}
