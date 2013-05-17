package com.puresol.utils.crypt;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This output stream scrambler puts bytes out into another stream. This
 * decorator encrypts the bytes with a given pattern on the fly.
 * 
 * @author Rick-Rainer Ludwig
 */
public class OutputStreamScrambler extends OutputStream {

	private int patternPosition;
	private final byte[] pattern;
	private final OutputStream outputStream;

	/**
	 * This is the constructor for this class.
	 * 
	 * @param pattern
	 *            is the pattern as byte array which is used for encryption.
	 *            This pattern is also used for decryption with
	 *            {@link InputStreamScrambler}.
	 * @param outputStream
	 *            si the {@link OutputStream} to write the encoded bytes to.
	 */
	public OutputStreamScrambler(byte[] pattern, OutputStream outputStream) {
		super();
		this.pattern = pattern;
		this.outputStream = outputStream;
		patternPosition = 0;
	}

	@Override
	public void write(int nextByte) throws IOException {
		nextByte ^= pattern[patternPosition];
		patternPosition++;
		if (patternPosition >= pattern.length) {
			patternPosition = 0;
		}
		outputStream.write(nextByte);
	}

}
