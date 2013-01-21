package com.puresol.coding.lang.c11.preprocessor.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.UnspecifiedSourceCodeLocation;

public class ReplacementParameterResultTest {

    private static ReplacementParameterResult invokeExtractParameterReplacements(
	    TokenStream tokenStream, int tokenId) throws Exception {
	ReplacementParameterResult parameters = ReplacementParameterResult
		.extractParameterReplacements(tokenStream, tokenId);
	return parameters;
    }

    @Test
    public void testExtractParameterReplacementsEmpty() throws Exception {
	TokenStream stream = new TokenStream();
	stream.add(new Token("name", "name", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	ReplacementParameterResult parameters = invokeExtractParameterReplacements(
		stream, 0);
	assertNotNull(parameters);
	assertEquals(0, parameters.getTokensToSkip());
    }

    @Test
    public void testExtractParameterReplacementsSimple() throws Exception {
	TokenStream stream = new TokenStream();
	stream.add(new Token("name", "name", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "param", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	ReplacementParameterResult parameters = invokeExtractParameterReplacements(
		stream, 0);
	assertNotNull(parameters);
	assertEquals(1, parameters.getNumberOfParameters());
	TokenStream parameterStream = parameters.getReplacement(0);
	assertEquals(1, parameterStream.size());
    }

    @Test
    public void testExtractParameterReplacementsMultiple() throws Exception {
	TokenStream stream = new TokenStream();
	stream.add(new Token("name", "name", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "param1", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "param2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "param3", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "param4", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	ReplacementParameterResult parameters = invokeExtractParameterReplacements(
		stream, 0);
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
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "1", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "+", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "param2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "param3", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "1", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", "2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1)));
	ReplacementParameterResult parameters = invokeExtractParameterReplacements(
		stream, 0);
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

}
