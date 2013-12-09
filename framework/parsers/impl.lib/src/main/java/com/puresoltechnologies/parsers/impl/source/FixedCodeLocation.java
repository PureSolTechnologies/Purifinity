package com.puresoltechnologies.parsers.impl.source;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.parsers.api.source.SourceCode;
import com.puresoltechnologies.parsers.api.source.SourceCodeLine;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;

/**
 * This source implementation is used for build-in sources like source
 * generators. This source is put into {@link TokenStream} and other objects
 * directly and it is expected, that load is not started anymore!
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class FixedCodeLocation extends AbstractCodeLocation {

	private static final long serialVersionUID = -694681290095697777L;

	private final SourceCode sourceCode;

	public FixedCodeLocation(SourceCode sourceCode) {
		this.sourceCode = sourceCode;
	}

	public FixedCodeLocation(String... lines) {
		SourceCodeImpl sourceCode = new SourceCodeImpl();
		int lineNum = 0;
		for (String line : lines) {
			if ((line != null) && (!line.isEmpty())) {
				lineNum++;
				sourceCode.addSourceCodeLine(new SourceCodeLineImpl(this,
						lineNum, line));
			}
		}
		this.sourceCode = sourceCode;
	}

	@Override
	public InputStream openStream() throws IOException {
		StringBuilder builder = new StringBuilder();
		for (SourceCodeLine line : sourceCode.getLines()) {
			builder.append(line.getLine());
		}
		return new ByteArrayInputStream(builder.toString().getBytes());
	}

	@Override
	public SourceCode loadSourceCode() throws IOException {
		return sourceCode;
	}

	@Override
	public String getHumanReadableLocationString() {
		return "build-in";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((sourceCode == null) ? 0 : sourceCode.hashCode());
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
		FixedCodeLocation other = (FixedCodeLocation) obj;
		if (sourceCode == null) {
			if (other.sourceCode != null)
				return false;
		} else if (!sourceCode.equals(other.sourceCode))
			return false;
		return true;
	}

	@Override
	public CodeLocation newRelativeSource(String relativePath) {
		throw new IllegalStateException(
				"Cannot provide a new relative source to a built-in source!");
	}

	@Override
	public String getName() {
		return "built-in source";
	}

	@Override
	public String getInternalLocation() {
		return "";
	}

	@Override
	public boolean isAvailable() {
		return true;
	}
}
