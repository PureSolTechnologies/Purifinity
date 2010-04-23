package com.puresol.coding.analysis;

import java.io.Serializable;

public class Symbol implements Serializable {

    private static final long serialVersionUID = 3026999694559102530L;

    private final SymbolType type;
    private final String name;
    private final CodeRange codeRange;
    private final int tokenId;

    public Symbol(SymbolType type, String name, CodeRange codeRange, int tokenId) {
	this.type = type;
	this.name = name;
	this.codeRange = codeRange;
	this.tokenId = tokenId;

    }

    /**
     * @return the type
     */
    public SymbolType getType() {
	return type;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @return the codeRange
     */
    public CodeRange getCodeRange() {
	return codeRange;
    }

    /**
     * @return the tokenId
     */
    public int getTokenId() {
	return tokenId;
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
	result = prime * result
		+ ((codeRange == null) ? 0 : codeRange.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + tokenId;
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
	Symbol other = (Symbol) obj;
	if (codeRange == null) {
	    if (other.codeRange != null)
		return false;
	} else if (!codeRange.equals(other.codeRange))
	    return false;
	if (name == null) {
	    if (other.name != null)
		return false;
	} else if (!name.equals(other.name))
	    return false;
	if (tokenId != other.tokenId)
	    return false;
	if (type == null) {
	    if (other.type != null)
		return false;
	} else if (!type.equals(other.type))
	    return false;
	return true;
    }

    @Override
    public Object clone() {
	return new Symbol(type, name, codeRange, tokenId);
    }
}
