package com.puresol.coding.lang.c11.preprocessor.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenMetaData;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.source.UnspecifiedSourceCodeLocation;

public class TreeMacroProcessorTest {

    private static List<TokenStream> invokeExtractParameterReplacements(
	    TokenStream tokenStream, int tokenId) throws Exception {
	Method extractParameterReplacements = TreeMacroProcessor.class
		.getDeclaredMethod("extractParameterReplacements",
			TokenStream.class, int.class);
	extractParameterReplacements.setAccessible(true);
	@SuppressWarnings("unchecked")
	List<TokenStream> parameters = (List<TokenStream>) extractParameterReplacements
		.invoke(null, tokenStream, tokenId);
	return parameters;
    }

    @Test
    public void testExtractParameterReplacementsEmpty() throws Exception {
	TokenStream stream = new TokenStream();
	stream.add(new Token("name", "name", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	List<TokenStream> parameters = invokeExtractParameterReplacements(
		stream, 0);
	assertNotNull(parameters);
	assertEquals(0, parameters.size());
    }

    @Test
    public void testExtractParameterReplacementsSimple() throws Exception {
	TokenStream stream = new TokenStream();
	stream.add(new Token("name", "name", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 1)));
	stream.add(new Token("name", "param", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 3, 1)));
	List<TokenStream> parameters = invokeExtractParameterReplacements(
		stream, 0);
	assertNotNull(parameters);
	assertEquals(1, parameters.size());
	TokenStream parameterStream = parameters.get(0);
	assertEquals(1, parameterStream.size());
    }

    @Test
    public void testExtractParameterReplacementsMultiple() throws Exception {
	TokenStream stream = new TokenStream();
	stream.add(new Token("name", "name", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 1, 1)));
	stream.add(new Token("name", "param1", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", "param2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", "param3", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", "param4", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 2, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 3, 1)));
	List<TokenStream> parameters = invokeExtractParameterReplacements(
		stream, 0);
	assertNotNull(parameters);
	assertEquals(4, parameters.size());
	TokenStream parameterStream = parameters.get(0);
	assertEquals(1, parameterStream.size());
	parameterStream = parameters.get(1);
	assertEquals(1, parameterStream.size());
	parameterStream = parameters.get(2);
	assertEquals(1, parameterStream.size());
	parameterStream = parameters.get(3);
	assertEquals(1, parameterStream.size());
    }

    @Test
    public void testExtractParameterReplacementsComplex() throws Exception {
	TokenStream stream = new TokenStream();
	stream.add(new Token("name", "name", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "1", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "+", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "param2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "param3", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "(", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "1", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", ",", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", "2", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	stream.add(new Token("name", ")", Visibility.VISIBLE,
		new TokenMetaData(new UnspecifiedSourceCodeLocation(), 1, 0, 1)));
	List<TokenStream> parameters = invokeExtractParameterReplacements(
		stream, 0);
	assertNotNull(parameters);
	assertEquals(4, parameters.size());
	TokenStream parameterStream = parameters.get(0);
	assertEquals(5, parameterStream.size());
	parameterStream = parameters.get(1);
	assertEquals(1, parameterStream.size());
	parameterStream = parameters.get(2);
	assertEquals(1, parameterStream.size());
	parameterStream = parameters.get(3);
	assertEquals(5, parameterStream.size());
    }

}
