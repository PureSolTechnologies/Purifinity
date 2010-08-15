package com.puresol.uhura.grammar.production;

public abstract class AbstractProductionElement implements ProductionElement {

	private final String name;
	private final String text;
	private final ProductionElementType type;
	private final Quantity quantity;

	public AbstractProductionElement(String name, String text,
			ProductionElementType type) {
		super();
		this.name = name;
		this.text = text;
		this.type = type;
		this.quantity = Quantity.EXPECT;
	}

	public AbstractProductionElement(String name, String text,
			ProductionElementType type, Quantity quantity) {
		super();
		this.name = name;
		this.text = text;
		this.type = type;
		this.quantity = quantity;
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
	public ProductionElementType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#getQuantity()
	 */
	@Override
	public Quantity getQuantity() {
		return quantity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.puresol.uhura.parser.ParserRuleElementInterface#toString()
	 */
	@Override
	public String toString() {
		return name + ": '" + text + "'" + quantity + "(" + type + ")";
	}

	public String toShortString() {
		if (name.isEmpty()) {
			return "'" + text + "'" + quantity;
		} else {
			return name + quantity;
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
		result = prime * result
				+ ((quantity == null) ? 0 : quantity.hashCode());
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
		AbstractProductionElement other = (AbstractProductionElement) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (quantity != other.quantity)
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

}
