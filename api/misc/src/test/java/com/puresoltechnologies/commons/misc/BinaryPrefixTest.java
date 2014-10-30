package com.puresoltechnologies.commons.misc;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class BinaryPrefixTest {

    @Test
    public void testToString() {
	for (BinaryPrefix fileSize : BinaryPrefix.values()) {
	    assertEquals(fileSize.getUnit(), fileSize.toString());
	}
    }

    @Test
    public void testOne() {
	BinaryPrefix one = BinaryPrefix.ONE;
	assertEquals("", one.getUnit());
	assertEquals("", one.getName());
	assertEquals(BigInteger.ONE, one.getBinaryFactor());
	assertEquals(BigInteger.ONE, one.getDecimalFactor());
    }

    @Test
    public void testKilo() {
	BinaryPrefix kilo = BinaryPrefix.KILO;
	assertEquals("k", kilo.getUnit());
	assertEquals("Kilo", kilo.getName());
	assertEquals(BigInteger.valueOf(1024).pow(1), kilo.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(3), kilo.getDecimalFactor());
    }

    @Test
    public void testMega() {
	BinaryPrefix mega = BinaryPrefix.MEGA;
	assertEquals("M", mega.getUnit());
	assertEquals("Mega", mega.getName());
	assertEquals(BigInteger.valueOf(1024).pow(2), mega.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(6), mega.getDecimalFactor());
    }

    @Test
    public void testGiga() {
	BinaryPrefix giga = BinaryPrefix.GIGA;
	assertEquals("G", giga.getUnit());
	assertEquals("Giga", giga.getName());
	assertEquals(BigInteger.valueOf(1024).pow(3), giga.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(9), giga.getDecimalFactor());
    }

    @Test
    public void testTera() {
	BinaryPrefix tera = BinaryPrefix.TERA;
	assertEquals("T", tera.getUnit());
	assertEquals("Tera", tera.getName());
	assertEquals(BigInteger.valueOf(1024).pow(4), tera.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(12), tera.getDecimalFactor());
    }

    @Test
    public void testPeta() {
	BinaryPrefix peta = BinaryPrefix.PETA;
	assertEquals("P", peta.getUnit());
	assertEquals("Peta", peta.getName());
	assertEquals(BigInteger.valueOf(1024).pow(5), peta.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(15), peta.getDecimalFactor());
    }

    @Test
    public void testExa() {
	BinaryPrefix exa = BinaryPrefix.EXA;
	assertEquals("E", exa.getUnit());
	assertEquals("Exa", exa.getName());
	assertEquals(BigInteger.valueOf(1024).pow(6), exa.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(18), exa.getDecimalFactor());
    }

    @Test
    public void testZetta() {
	BinaryPrefix zetta = BinaryPrefix.ZETTA;
	assertEquals("Z", zetta.getUnit());
	assertEquals("Zetta", zetta.getName());
	assertEquals(BigInteger.valueOf(1024).pow(7), zetta.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(21), zetta.getDecimalFactor());
    }

    @Test
    public void testYotta() {
	BinaryPrefix yotta = BinaryPrefix.YOTTA;
	assertEquals("Y", yotta.getUnit());
	assertEquals("Yotta", yotta.getName());
	assertEquals(BigInteger.valueOf(1024).pow(8), yotta.getBinaryFactor());
	assertEquals(BigInteger.TEN.pow(24), yotta.getDecimalFactor());
    }
}
