package com.puresoltechnologies.purifinity.framework.lang.c11.preprocessor.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.puresoltechnologies.parsers.impl.grammar.token.Visibility;
import com.puresoltechnologies.parsers.impl.lexer.Token;
import com.puresoltechnologies.parsers.impl.lexer.TokenMetaData;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;
import com.puresoltechnologies.purifinity.framework.lang.c11.preprocessor.internal.ReplacementParameterResult;

public class ReplacementParameterResultTest {

	@Test
	public void testExtractParameterReplacementsEmpty() throws Exception {
		TokenStream stream = new TokenStream();
		stream.add(new Token("name", "name", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		ReplacementParameterResult parameters = ReplacementParameterResult
				.extractParameterReplacements(stream, 0);
		assertNotNull(parameters);
		assertEquals(0, parameters.getTokensToSkip());
	}

	@Test
	public void testExtractParameterReplacementsSimple() throws Exception {
		TokenStream stream = new TokenStream();
		stream.add(new Token("name", "name", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "(", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "param", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ")", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		ReplacementParameterResult parameters = ReplacementParameterResult
				.extractParameterReplacements(stream, 0);
		assertNotNull(parameters);
		assertEquals(1, parameters.getNumberOfParameters());
		TokenStream parameterStream = parameters.getReplacement(0);
		assertEquals(1, parameterStream.size());
	}

	@Test
	public void testExtractParameterReplacementsMultiple() throws Exception {
		TokenStream stream = new TokenStream();
		stream.add(new Token("name", "name", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "(", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "param1", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ",", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "param2", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ",", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "param3", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ",", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "param4", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ")", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		ReplacementParameterResult parameters = ReplacementParameterResult
				.extractParameterReplacements(stream, 0);
		assertNotNull(parameters);
		assertEquals(4, parameters.getNumberOfParameters());
		TokenStream parameterStream = parameters.getReplacement(0);
		assertEquals(1, parameterStream.size());
		parameterStream = parameters.getReplacement(1);
		assertEquals(1, parameterStream.size());
		parameterStream = parameters.getReplacement(2);
		assertEquals(1, parameterStream.size());
		parameterStream = parameters.getReplacement(3);
		assertEquals(1, parameterStream.size());
	}

	@Test
	public void testExtractParameterReplacementsComplex() throws Exception {
		TokenStream stream = new TokenStream();
		stream.add(new Token("name", "name", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "(", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "(", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "1", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "+", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "2", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ")", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ",", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "param2", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ",", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "param3", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ",", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "(", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "1", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ",", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "2", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ")", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", ")", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		ReplacementParameterResult parameters = ReplacementParameterResult
				.extractParameterReplacements(stream, 0);
		assertNotNull(parameters);
		assertEquals(4, parameters.getNumberOfParameters());
		TokenStream parameterStream = parameters.getReplacement(0);
		assertEquals(5, parameterStream.size());
		parameterStream = parameters.getReplacement(1);
		assertEquals(1, parameterStream.size());
		parameterStream = parameters.getReplacement(2);
		assertEquals(1, parameterStream.size());
		parameterStream = parameters.getReplacement(3);
		assertEquals(5, parameterStream.size());
	}

	@Test
	public void testExtractParameterReplacementsInvalid() throws Exception {
		TokenStream stream = new TokenStream();
		stream.add(new Token("name", "name", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		stream.add(new Token("name", "(", Visibility.VISIBLE,
				new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 0)));
		ReplacementParameterResult parameters = ReplacementParameterResult
				.extractParameterReplacements(stream, 0);
		assertNull(parameters);
	}

}
