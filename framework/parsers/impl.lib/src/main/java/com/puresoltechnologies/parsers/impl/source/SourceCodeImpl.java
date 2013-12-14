package com.puresoltechnologies.parsers.impl.source;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.commons.misc.HashAlgorithm;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.commons.utils.data.HashCodeGenerator;
import com.puresoltechnologies.commons.utils.io.LineTerminator;
import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.api.source.SourceCodeLine;

/**
 * This class represents a whole source code from a file and additional later
 * changes through a preprocessor.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SourceCodeImpl implements SourceCode, Serializable, Cloneable {

	private static final long serialVersionUID = -4132802914950017966L;

	public static SourceCode fromStringArray(String... lines) {
		try {
			return new FixedCodeLocation(lines).loadSourceCode();
		} catch (IOException e) {
			throw new RuntimeException(
					"A build-in source has no IO traffic normally. So this should not happen.");
		}
	}

	public static SourceCode read(InputStream inputStream, CodeLocation source)
			throws IOException {
		Reader reader = new InputStreamReader(inputStream);
		try {
			return read(reader, source);
		} finally {
			reader.close();
		}
	}

	public static SourceCode read(Reader reader, CodeLocation source)
			throws IOException {
		char[] buffer = new char[4096];
		SourceCodeImpl code = new SourceCodeImpl();
		int length;
		StringBuffer stringBuffer = new StringBuffer();
		do {
			length = reader.read(buffer);
			if (length > 0) {
				stringBuffer.append(buffer, 0, length);
			}
		} while (length == buffer.length);
		int lineNumber = 0;
		while (true) {
			lineNumber++;
			String br = findNextLineBreak(stringBuffer);
			if (br.isEmpty()) {
				String line = stringBuffer.toString();
				code.addSourceCodeLine(new SourceCodeLineImpl(source,
						lineNumber, line));
				break;
			} else {
				int pos = stringBuffer.indexOf(br);
				String line = stringBuffer.substring(0, pos + br.length());
				code.addSourceCodeLine(new SourceCodeLineImpl(source,
						lineNumber, line));
				stringBuffer.delete(0, pos + br.length());
			}
		}
		return code;
	}

	private static String findNextLineBreak(StringBuffer stringBuffer) {
		String retVal = "";
		int pos = stringBuffer.length() - 1;
		int currPos = stringBuffer.indexOf("\r\n");
		if ((currPos < pos) && (currPos >= 0)) {
			pos = currPos;
			retVal = "\r\n";
		}
		currPos = stringBuffer.indexOf("\n");
		if ((currPos < pos) && (currPos >= 0)) {
			pos = currPos;
			retVal = "\n";
		}
		currPos = stringBuffer.indexOf("\r");
		if ((currPos < pos) && (currPos >= 0)) {
			pos = currPos;
			retVal = "\r";
		}
		return retVal;
	}

	private final List<SourceCodeLine> lines = new ArrayList<SourceCodeLine>();
	private HashId hashId = null;

	public SourceCodeImpl() {
		super();
	}

	@Override
	public List<SourceCodeLine> getLines() {
		return lines;
	}

	public void addSourceCodeLine(SourceCodeLine line) {
		lines.add(line);
		hashId = null;
	}

	public void addSourceCode(SourceCode newCode) {
		for (SourceCodeLine line : newCode.getLines()) {
			addSourceCodeLine(line);
		}
	}

	/**
	 * Generated a hash id for the sourceCode provided. This hash code may be
	 * used as key to find the source code, an analysis or something else
	 * related to the source code later on in a file store.
	 * 
	 * @param sourceCode
	 * @return
	 */
	private synchronized void generateHashId() {
		if (hashId == null) {
			StringBuffer buffer = new StringBuffer();
			for (SourceCodeLine line : getLines()) {
				buffer.append(line.getLine());
			}
			hashId = new HashId(HashAlgorithm.SHA256, HashCodeGenerator.get(
					HashAlgorithm.SHA256, buffer.toString()));
		}
	}

	@Override
	public HashId getHashId() {
		if (hashId == null) {
			generateHashId();
		}
		return hashId;
	}

	public boolean removeLineTerminatorAtLastLine() {
		if (lines.size() == 0) {
			throw new IllegalStateException(
					"The source code must have at least on line of code!");
		}
		SourceCodeLine lastLine = lines.get(lines.size() - 1);
		String text = lastLine.getLine();
		for (LineTerminator terminator : LineTerminator.values()) {
			if (text.endsWith(terminator.getCRString())) {
				lines.remove(lastLine);
				lastLine = new SourceCodeLineImpl(lastLine.getSource(),
						lastLine.getLineNumber(), text.substring(0,
								text.length()
										- terminator.getCRString().length()));
				lines.add(lastLine);
				hashId = null;
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lines == null) ? 0 : lines.hashCode());
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
		SourceCodeImpl other = (SourceCodeImpl) obj;
		if (lines == null) {
			if (other.lines != null)
				return false;
		} else if (!lines.equals(other.lines))
			return false;
		return true;
	}

	@Override
	public SourceCode clone() {
		try {
			SourceCodeImpl cloned = (SourceCodeImpl) super.clone();
			cloned.lines.addAll(cloned.lines);
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (SourceCodeLine line : lines) {
			buffer.append(line.getSource());
			buffer.append(":");
			buffer.append(line.getLineNumber());
			buffer.append("\t");
			buffer.append(line.getLine().replaceAll("\\n", "\\\\n"));
			buffer.append("\n");
		}
		return buffer.toString();
	}

	public void clear() {
		hashId = null;
		lines.clear();
	}
}
