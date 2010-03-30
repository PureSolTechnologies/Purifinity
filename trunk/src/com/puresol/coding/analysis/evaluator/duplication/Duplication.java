package com.puresol.coding.analysis.evaluator.duplication;

import com.puresol.coding.analysis.CodeRange;

/**
 * This class keeps the information of a single duplication found by a scanner.
 * Both code ranges for the duplication are named left and right for the
 * recommended presentation style on the screen.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Duplication implements Comparable<Duplication> {

	private CodeRange left;
	private CodeRange right;
	private int matchSize;
	private double correlation;

	public Duplication(CodeRange left, CodeRange right, int matchSize) {
		this.left = left;
		this.right = right;
		this.matchSize = matchSize;
		init();
	}

	private void init() {
		correlation = left.getStop() - left.getStart();
		correlation += right.getStop() - right.getStart();
		correlation /= 2;
		correlation = (double) matchSize / correlation;
	}

	public CodeRange getLeft() {
		return left;
	}

	public CodeRange getRight() {
		return right;
	}

	public int getMatchSize() {
		return matchSize;
	}

	public double getCorrelation() {
		return correlation;
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
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + matchSize;
		result = prime * result + ((right == null) ? 0 : right.hashCode());
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
		Duplication other = (Duplication) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (matchSize != other.matchSize)
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	@Override
	public int compareTo(Duplication other) {
		if (this.matchSize < other.matchSize) {
			return -1;
		}
		if (this.matchSize > other.matchSize) {
			return +1;
		}
		return 0;
	}

}
