package com.puresol.uhura.parser.packrat;

/**
 * This class is used to put a status information into the memoizer of a packrat
 * parser. This status is associated with a position and a production.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
enum ParserStatus {
	/**
	 * Means a processing was neither done nor tried.
	 */
	NOT_PROCESSED,

	/**
	 * Means processing was successfully done.
	 */
	SUCCEEDED,

	/**
	 * Means a process is trying to process this position without knowing to
	 * have a recursion here.
	 */
	NEW_IN_PROCESS,

	/**
	 * A process already failed to parse at this position the production
	 * associated.
	 */
	FAILED,

	/**
	 * Means that a recursion was found.
	 */
	FOUND_RECURSION;

	@Override
	public String toString() {
		String result = "";
		switch (this) {
		case NOT_PROCESSED:
			result += "not processed";
			break;
		case SUCCEEDED:
			result += "succeeded";
			break;
		case NEW_IN_PROCESS:
			result += "in process";
			break;
		case FAILED:
			result += "failed";
			break;
		case FOUND_RECURSION:
			result += "found recursion";
			break;
		default:
			throw new RuntimeException("Unknown and undefined state!");
		}
		return result;
	}
}
