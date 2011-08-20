package com.puresol.uhura.parser.packrat;

import java.io.Serializable;

import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;

class MemoEntry implements Serializable, Comparable<MemoEntry> {

	private static final long serialVersionUID = 2910217488523982637L;

	static MemoEntry success(int deltaPosition, int deltaId, int deltaLine,
			ParserTree tree) {
		return new MemoEntry(deltaPosition, deltaId, deltaLine, tree);
	}

	static MemoEntry failed() {
		return new MemoEntry(-1, -1, -1, Status.FAILED);
	}

	static MemoEntry create(LR lr) {
		return new MemoEntry(-1, -1, -1, lr);
	}

	private int deltaPosition;
	private int deltaId;
	private int deltaLine;
	private Object answer;

	private MemoEntry(int deltaPosition, int deltaId, int deltaLine,
			Object answer) {
		super();
		this.deltaPosition = deltaPosition;
		this.deltaId = deltaId;
		this.deltaLine = deltaLine;
		this.answer = answer;
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

	void setAnswer(Object answer) {
		this.answer = answer;
	}

	Object getAnswer() {
		return answer;
	}

	void add(MemoEntry progress) throws ParserException {
		if ((deltaPosition < 0) || (deltaId < 0) || (deltaLine < 0)) {
			throw new ParserException("Negative progress is not supported!");
		}
		deltaPosition += progress.deltaPosition;
		deltaId += progress.deltaId;
		deltaLine += progress.deltaLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + deltaPosition;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
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
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
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

	public void set(MemoEntry ans) {
		this.deltaPosition = ans.deltaPosition;
		this.deltaId = ans.deltaId;
		this.deltaLine = ans.deltaLine;
		this.answer = ans.answer;
	}

	@Override
	public String toString() {
		String result = "dPos: " + deltaPosition;
		result += "; dId: " + deltaId;
		result += "; dLine: " + deltaLine;
		result += "; Answer: " + answer;
		return result;
	}
}
