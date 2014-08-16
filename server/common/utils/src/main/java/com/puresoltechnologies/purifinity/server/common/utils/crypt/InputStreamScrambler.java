package com.puresoltechnologies.purifinity.server.common.utils.crypt;

import java.io.IOException;
import java.io.InputStream;

/**
 * This input stream scrambler reads bytes from another input stream and decodes
 * bytes which were encoded with {@link OutputStreamScrambler} on the fly.
 * 
 * @author Rick-Rainer Ludwig
 */
public class InputStreamScrambler extends InputStream {

	private int patternPosition;
	private final byte[] pattern;
	private final InputStream inputStream;

	/**
	 * This is the constructor for this class.
	 * 
	 * @param pattern
	 *            is the pattern as byte array which is used for decryption.
	 *            This pattern must be the same as the pattern used for
	 *            encryption with {@link OutputStreamScrambler}.
	 * @param inputStream
	 *            is the {@link InputStream} to read the bytes for decryption
	 *            from.
	 */
	public InputStreamScrambler(byte[] pattern, InputStream inputStream) {
		super();
		this.pattern = pattern;
		this.inputStream = inputStream;
		patternPosition = 0;
	}

	@Override
	public int read() throws IOException {
		int nextByte = inputStream.read();
		if (nextByte < 0) {
			return nextByte;
		}
		nextByte ^= pattern[patternPosition];
		patternPosition++;
		if (patternPosition >= pattern.length) {
			patternPosition = 0;
		}
		return nextByte;
	}

}
