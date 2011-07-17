package com.puresol.uhura.parser.packrat;

/**
 * This class is used to put a status information into the memoizer of a packrat
 * parser. This status is associated with a position and a production.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
class ParserStatus {
	/**
	 * Means a processing was neither done nor tried.
	 */
	public static final int NOT_PROCESSED = 0;

	/**
	 * Means processing was successfully done.
	 */
	public static final int SUCCEEDED = 1;

	/**
	 * Means a process is trying to process this position without knowing to
	 * have a recursion here.
	 */
	public static final int IN_PROCESS = 2;

	/**
	 * A process already failed to parse at this position the production
	 * associated.
	 */
	public static final int FAILED = 3;

	/**
	 * For storing the status.
	 */
	private final int status;

	public ParserStatus(int status) {
		super();
		this.status = status;
	}

	public ParserStatus(int status, int counter) {
		super();
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	@Override
	public String toString() {
		String result = "";
		switch (status) {
		case NOT_PROCESSED:
			result += "not processed";
			break;
		case SUCCEEDED:
			result += "succeeded";
			break;
		case IN_PROCESS:
			result += "in process";
			break;
		case FAILED:
			result += "failed";
			break;
		}
		return result;
	}
}
