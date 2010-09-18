package com.puresol.uhura.grammar.production;

public abstract class AbstractConstruction implements Construction {

	private static final long serialVersionUID = -8190479779174841005L;

	private final String name;
	private final String text;
	private final ConstructionType type;

	public AbstractConstruction(String name, String text, ConstructionType type) {
		super();
		this.name = name;
		this.text = text;
		this.type = type;
	}

	/**
	 * @return the typeId
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#getText()
	 */
	@Override
	public String getText() {
		return text;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#getType()
	 */
	@Override
	public ConstructionType getType() {
		return type;
	}

	@Override
	public boolean isTerminal() {
		return (type != ConstructionType.NON_TERMINAL);
	}

	@Override
	public boolean isNonTerminal() {
		return (type == ConstructionType.NON_TERMINAL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#toString()
	 */
	@Override
	public String toString() {
		return name + ": '" + text + "' (" + type + ")";
	}

	public String toShortString() {
		if (name.isEmpty()) {
			return "'" + text + "'";
		} else {
			return name;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		AbstractConstruction other = (AbstractConstruction) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (type != other.type)
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
		result = this.getType().compareTo(other.getType());
		if (result != 0) {
			return result;
		}
		return this.getText().compareTo(other.getText());
	}
}
