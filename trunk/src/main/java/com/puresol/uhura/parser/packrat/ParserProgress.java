package com.puresol.uhura.parser.packrat;

import java.io.Serializable;

import com.puresol.uhura.parser.ParserTree;

class ParserProgress implements Serializable, Comparable<ParserProgress> {

	private static final long serialVersionUID = 2910217488523982637L;

	static ParserProgress success(int deltaPosition, int deltaId,
			int deltaLine, ParserTree tree) {
		return new ParserProgress(deltaPosition, deltaId, deltaLine, tree);
	}

	static ParserProgress failure() {
		return new ParserProgress(-1, -1, -1, null);
	}

	public static ParserProgress none() {
		return new ParserProgress(0, 0, 0, null);
	}

	private int deltaPosition;
	private int deltaId;
	private int deltaLine;
	private ParserTree tree;

	private ParserProgress(int deltaPosition, int deltaId, int deltaLine,
			ParserTree tree) {
		super();
		this.deltaPosition = deltaPosition;
		this.deltaId = deltaId;
		this.deltaLine = deltaLine;
		this.tree = tree;
	}

	int getDeltaPosition() {
		return deltaPosition;
	}

	int getDeltaId() {
		return deltaId;
	}

	int getDeltaLine() {
		return deltaLine;
	}

	ParserTree getTree() {
		return tree;
	}

	boolean madeProgress() {
		return (deltaLine >= 0) && (deltaId > 0) && (deltaPosition > 0)
				&& (tree != null);
	}

	boolean failed() {
		return (deltaLine < 0) || (deltaId < 0) || (deltaPosition < 0);
	}

	boolean succeeded() {
		return !failed();
	}

	void add(ParserProgress progress) {
		deltaPosition += progress.deltaPosition;
		deltaId += progress.deltaId;
		deltaLine += progress.deltaLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deltaId;
		result = prime * result + deltaLine;
		result = prime * result + deltaPosition;
		result = prime * result + ((tree == null) ? 0 : tree.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParserProgress other = (ParserProgress) obj;
		if (deltaId != other.deltaId)
			return false;
		if (deltaLine != other.deltaLine)
			return false;
		if (deltaPosition != other.deltaPosition)
			return false;
		if (tree == null) {
			if (other.tree != null)
				return false;
		} else if (!tree.equals(other.tree))
			return false;
		return true;
	}

	@Override
	public int compareTo(ParserProgress other) {
		if (this.deltaPosition < other.deltaPosition)
			return -1;
		else if (this.deltaPosition > other.deltaPosition)
			return 1;
		return 0;
	}

}
