package com.puresol.uhura.parser.packrat;

import java.io.Serializable;

public class LR implements Serializable {

	private static final long serialVersionUID = -5890456350006297830L;

	private boolean detected = false;
	private MemoEntry seed;
	private final String production;
	private Head head;
	private final LR next;

	public LR(MemoEntry seed, String production, Head head, LR next) {
		super();
		this.seed = seed;
		this.production = production;
		this.head = head;
		this.next = next;
	}

	void setDetected(boolean detected) {
		this.detected = detected;
	}

	boolean isDetected() {
		return detected;
	}

	void setSeed(MemoEntry seed) {
		this.seed = seed;
	}

	MemoEntry getSeed() {
		return seed;
	}

	String getProduction() {
		return production;
	}

	void setHead(Head head) {
		this.head = head;
	}

	Head getHead() {
		return head;
	}

	LR getNext() {
		return next;
	}

}
