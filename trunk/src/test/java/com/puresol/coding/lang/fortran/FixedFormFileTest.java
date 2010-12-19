package com.puresol.coding.lang.fortran;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.regex.Pattern;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.FortranGrammar;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.Token;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.utils.PersistenceException;

public class FixedFormFileTest {

	private Pattern getPattern(String name) {
		try {
			Field commentPattern = FixedFormFile.class.getDeclaredField(name);
			commentPattern.setAccessible(true);
			return (Pattern) commentPattern.get(0);
		} catch (SecurityException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
		return null;
	}

	@Test
	public void testCommentPattern() {
		Pattern COMMENT_PATTERN = getPattern("COMMENT_PATTERN");
		assertTrue(COMMENT_PATTERN.matcher("C A comment!").find());
		assertFalse(COMMENT_PATTERN.matcher(" C A comment!").find());

		assertTrue(COMMENT_PATTERN.matcher("c A comment!").find());
		assertFalse(COMMENT_PATTERN.matcher(" c A comment!").find());

		assertTrue(COMMENT_PATTERN.matcher("* A comment!").find());
		assertFalse(COMMENT_PATTERN.matcher(" * A comment!").find());

		assertTrue(COMMENT_PATTERN.matcher("! A comment!").find());
		assertTrue(COMMENT_PATTERN.matcher(" ! A comment!").find());
		assertTrue(COMMENT_PATTERN.matcher("  ! A comment!").find());
		assertTrue(COMMENT_PATTERN.matcher("   ! A comment!").find());
		assertTrue(COMMENT_PATTERN.matcher("    ! A comment!").find());
		assertFalse(COMMENT_PATTERN.matcher("     ! A comment!").find());
	}

	@Test
	public void testLabelPattern() {
		Pattern LABEL_PATTERN = getPattern("LABEL_PATTERN");

		assertTrue(LABEL_PATTERN.matcher("1     ").find());
		assertTrue(LABEL_PATTERN.matcher(" 2    ").find());
		assertTrue(LABEL_PATTERN.matcher("  3   ").find());
		assertTrue(LABEL_PATTERN.matcher("   4  ").find());
		assertTrue(LABEL_PATTERN.matcher("    5 ").find());
		assertTrue(LABEL_PATTERN.matcher("    50").find());
		assertTrue(LABEL_PATTERN.matcher("12    ").find());
		assertTrue(LABEL_PATTERN.matcher(" 23   ").find());
		assertTrue(LABEL_PATTERN.matcher("  34  ").find());
		assertTrue(LABEL_PATTERN.matcher("   45 ").find());
		assertTrue(LABEL_PATTERN.matcher("   450").find());
		assertTrue(LABEL_PATTERN.matcher("123   ").find());
		assertTrue(LABEL_PATTERN.matcher(" 234  ").find());
		assertTrue(LABEL_PATTERN.matcher("  345 ").find());
		assertTrue(LABEL_PATTERN.matcher("  3450").find());
		assertTrue(LABEL_PATTERN.matcher("1234  ").find());
		assertTrue(LABEL_PATTERN.matcher(" 2345 ").find());
		assertTrue(LABEL_PATTERN.matcher(" 23450").find());
		assertTrue(LABEL_PATTERN.matcher("12345 ").find());
		assertTrue(LABEL_PATTERN.matcher("123450").find());

		assertFalse(LABEL_PATTERN.matcher("      ").find());
		assertFalse(LABEL_PATTERN.matcher("     0").find());
	}

	@Test
	public void testContinuationPattern() {
		Pattern CONTINUATION_PATTERN = getPattern("CONTINUATION_PATTERN");
		assertTrue(CONTINUATION_PATTERN.matcher("     $").find());
		assertTrue(CONTINUATION_PATTERN.matcher("     *").find());
		assertFalse(CONTINUATION_PATTERN.matcher("     0").find());
	}

	@Test
	public void testEmptyPattern() {
		Pattern EMPTY_PATTERN = getPattern("EMPTY_PATTERN");
		assertTrue(EMPTY_PATTERN.matcher("      ").find());
	}

	@Test
	public void testBlankLinePattern() {
		Pattern BLANK_LINE_PATTERN = getPattern("BLANK_LINE_PATTERN");
		assertTrue(BLANK_LINE_PATTERN.matcher("      ").find());
		assertTrue(BLANK_LINE_PATTERN.matcher(" \t\n").find());
	}

	@Test
	public void testIsFixedForm() {
		try {
			File file = new File(
					"src/test/java/com/puresol/coding/lang/fortran/samples/FixedFormSample.f");
			assertTrue(file.exists());
			FixedFormFile fixedFormFile = new FixedFormFile(file);
			assertTrue(fixedFormFile.isValid());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testScan() {
		try {
			File file = new File(
					"src/test/java/com/puresol/coding/lang/fortran/samples/FixedFormSample.f");
			assertTrue(file.exists());
			FixedFormFile fixedFormFile = new FixedFormFile(file);
			TokenStream tokenStream = fixedFormFile.scan(FortranGrammar
					.getInstance().getLexer());
			for (Token token : tokenStream) {
				System.out.print(token);
				System.out.print("\t");
				System.out.print(token.getVisibility());
				System.out.print("\t");
				System.out.println(token.getMetaData());
			}
			assertEquals("WHITESPACE", tokenStream.get(0).getName());
			assertEquals("COMMENT_LINE", tokenStream.get(6).getName());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
