package com.puresol.coding;

public class Duplication implements Comparable<Duplication> {

	private CodeRange left;
	private CodeRange right;
	private int rangeSize;

	public Duplication(CodeRange left, CodeRange right, int rangeSize) {
		this.left = left;
		this.right = right;
		this.rangeSize = rangeSize;
	}

	public CodeRange getLeft() {
		return left;
	}

	public CodeRange getRight() {
		return right;
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
		result = prime * result + rangeSize;
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
		if (rangeSize != other.rangeSize)
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
		if (this.rangeSize < other.rangeSize) {
			return -1;
		}
		if (this.rangeSize > other.rangeSize) {
			return +1;
		}
		return 0;
	}

}
