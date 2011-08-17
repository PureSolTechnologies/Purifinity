package com.puresol.uhura.parser.packrat;

import java.io.Serializable;

/**
 * This class represents a LR element in packrat parser. This LR element is used
 * to track the left recursion growing process.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class LR implements Serializable, Cloneable {

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

	@Override
	public String toString() {
		String result = production;
		if (detected)
			result += " ";
		else
			result += "*";
		result += "; seed: " + seed;
		result += "; head: " + head;
		if (next != null)
			result += "; next: " + next.getProduction();
		return result;
	}

	@Override
	public LR clone() {
		MemoEntry clonedSeed = null;
		if (seed != null)
			clonedSeed = seed.clone();
		Head clonedHead = null;
		if (head != null)
			clonedHead = head.clone();
		LR clonedNext = null;
		if (next != null)
			clonedNext = next.clone();
		return new LR(clonedSeed, production, clonedHead, clonedNext);
	}
}
