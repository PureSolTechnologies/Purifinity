package com.puresol.uhura.parser.packrat;

import java.io.Serializable;

import com.puresol.uhura.parser.ParserTree;

class MemoEntry implements Serializable, Comparable<MemoEntry> {

	private static final long serialVersionUID = 2910217488523982637L;

	static MemoEntry success(int deltaPosition, int deltaId, int deltaLine,
			ParserTree tree) {
		return new MemoEntry(deltaPosition, deltaId, deltaLine, tree, null,
				Status.SUCCEEDED);
	}

	static MemoEntry failure() {
		return new MemoEntry(-1, -1, -1, null, null, Status.FAILED);
	}

	static MemoEntry failure(LR lr) {
		return new MemoEntry(-1, -1, -1, null, lr, Status.FAILED);
	}

	static MemoEntry none() {
		return new MemoEntry(0, 0, 0, null, null, Status.NONE);
	}

	private int deltaPosition;
	private int deltaId;
	private int deltaLine;
	private ParserTree tree;
	private LR lr;
	private Status status;

	private MemoEntry(int deltaPosition, int deltaId, int deltaLine,
			ParserTree tree, LR lr, Status status) {
		super();
		this.deltaPosition = deltaPosition;
		this.deltaId = deltaId;
		this.deltaLine = deltaLine;
		this.tree = tree;
		this.lr = lr;
		this.status = status;
	}

	void setDeltaPosition(int deltaPosition) {
		this.deltaPosition = deltaPosition;
	}

	int getDeltaPosition() {
		return deltaPosition;
	}

	int getDeltaId() {
		return deltaId;
	}

	void setDeltaId(int deltaId) {
		this.deltaId = deltaId;
	}

	int getDeltaLine() {
		return deltaLine;
	}

	void setDeltaLine(int deltaLine) {
		this.deltaLine = deltaLine;
	}

	void setTree(ParserTree tree) {
		this.tree = tree;
	}

	ParserTree getTree() {
		return tree;
	}

	void setLR(LR lr) {
		this.lr = lr;
	}

	LR getLR() {
		return lr;
	}

	void setStatus(Status status) {
		this.status = status;
	}

	Status getStatus() {
		return status;
	}

	boolean madeProgress() {
		return (deltaPosition > 0) && (tree != null)
				&& (status == Status.SUCCEEDED);
	}

	boolean failed() {
		return (status == Status.FAILED);
	}

	boolean succeeded() {
		return (status == Status.SUCCEEDED);
	}

	void add(MemoEntry progress) {
		deltaPosition += progress.deltaPosition;
		deltaId += progress.deltaId;
		deltaLine += progress.deltaLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MemoEntry other = (MemoEntry) obj;
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
	public int compareTo(MemoEntry other) {
		if (this.deltaPosition < other.deltaPosition)
			return -1;
		else if (this.deltaPosition > other.deltaPosition)
			return 1;
		return 0;
	}
}
