package com.puresol.coding.evaluator.gotos;

import java.io.Serializable;

import com.puresol.coding.analysis.CodeRange;

/**
 * This class represents a single goto label found by GotoEvaluator.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FoundLabel implements Serializable {

	private static final long serialVersionUID = 4456287174201314363L;

	private final CodeRange codeRange;
	private final int tokenId;
	private final String labelName;

	public FoundLabel(CodeRange codeRange, int tokenId, String labelName) {
		this.codeRange = codeRange;
		this.tokenId = tokenId;
		this.labelName = labelName;
	}

	public CodeRange getCodeRange() {
		return codeRange;
	}

	public int getTokenId() {
		return tokenId;
	}

	public String getLabelName() {
		return labelName;
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
		result = prime * result
				+ ((labelName == null) ? 0 : labelName.hashCode());
		result = prime * result + tokenId;
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
		FoundLabel other = (FoundLabel) obj;
		if (codeRange == null) {
			if (other.codeRange != null)
				return false;
		} else if (!codeRange.equals(other.codeRange))
			return false;
		if (labelName == null) {
			if (other.labelName != null)
				return false;
		} else if (!labelName.equals(other.labelName))
			return false;
		if (tokenId != other.tokenId)
			return false;
		return true;
	}

}
