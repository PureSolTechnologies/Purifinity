package com.puresol.uhura.source;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.puresol.data.HashCodeGenerator;
import com.puresol.utils.HashAlgorithm;
import com.puresol.utils.HashId;

/**
 * This class represents a whole source code from a file and additional later
 * changes through a preprocessor.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SourceCode implements Serializable, Cloneable {

    private static final long serialVersionUID = -4132802914950017966L;

    public static SourceCode fromStringArray(String... lines) {
	try {
	    return new FixedCodeLocation(lines).load();
	} catch (IOException e) {
	    throw new RuntimeException(
		    "A build-in source has no IO traffic normally. So this should not happen.");
	}
    }

    public static SourceCode read(InputStream inputStream,
	    CodeLocation source) throws IOException {
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
	SourceCode code = new SourceCode();
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
		code.addSourceCodeLine(new SourceCodeLine(source, lineNumber,
			line));
		break;
	    } else {
		int pos = stringBuffer.indexOf(br);
		String line = stringBuffer.substring(0, pos + br.length());
		code.addSourceCodeLine(new SourceCodeLine(source, lineNumber,
			line));
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

    private final List<SourceCodeLine> source = new ArrayList<SourceCodeLine>();
    private HashId hashId = null;

    public SourceCode() {
	super();
    }

    public List<SourceCodeLine> getSource() {
	return source;
    }

    public void addSourceCodeLine(SourceCodeLine line) {
	source.add(line);
    }

    public void addSourceCode(SourceCode newCode) {
	for (SourceCodeLine line : newCode.getSource()) {
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
	    for (SourceCodeLine line : getSource()) {
		buffer.append(line.getLine());
	    }
	    hashId = new HashId(HashAlgorithm.SHA256, HashCodeGenerator.get(
		    HashAlgorithm.SHA256, buffer.toString()));
	}
    }

    public HashId getHashId() {
	if (hashId == null) {
	    generateHashId();
	}
	return hashId;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((source == null) ? 0 : source.hashCode());
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
	SourceCode other = (SourceCode) obj;
	if (source == null) {
	    if (other.source != null)
		return false;
	} else if (!source.equals(other.source))
	    return false;
	return true;
    }

    @Override
    public SourceCode clone() {
	try {
	    SourceCode cloned = (SourceCode) super.clone();
	    cloned.source.addAll(cloned.source);
	    return cloned;
	} catch (CloneNotSupportedException e) {
	    throw new RuntimeException(e);
	}
    }

    @Override
    public String toString() {
	StringBuffer buffer = new StringBuffer();
	for (SourceCodeLine line : source) {
	    buffer.append(line.getSource());
	    buffer.append(":");
	    buffer.append(line.getLineNumber());
	    buffer.append("\t");
	    buffer.append(line.getLine().replaceAll("\\n", "\\\\n"));
	    buffer.append("\n");
	}
	return buffer.toString();
    }

}
