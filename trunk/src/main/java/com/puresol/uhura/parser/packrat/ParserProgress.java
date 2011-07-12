package com.puresol.uhura.parser.packrat;

class ParserProgress {

	static ParserProgress success(int deltaPosition, int deltaId, int deltaLine) {
		return new ParserProgress(deltaPosition, deltaId, deltaLine);
	}

	static ParserProgress failure() {
		return new ParserProgress(-1, -1, -1);
	}

	public static ParserProgress none() {
		return new ParserProgress(0, 0, 0);
	}

	private int deltaPosition;
	private int deltaId;
	private int deltaLine;

	private ParserProgress(int deltaPosition, int deltaId, int deltaLine) {
		super();
		this.deltaPosition = deltaPosition;
		this.deltaId = deltaId;
		this.deltaLine = deltaLine;
	}

	int getDeltaPosition() {
		return deltaPosition;
	}

	void setDeltaPosition(int deltaPosition) {
		this.deltaPosition = deltaPosition;
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

	boolean madeProgress() {
		return (deltaLine >= 0) && (deltaId > 0) && (deltaPosition > 0);
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

}
