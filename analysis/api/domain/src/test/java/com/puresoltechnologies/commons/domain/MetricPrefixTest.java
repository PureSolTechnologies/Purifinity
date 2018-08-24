package com.puresoltechnologies.commons.domain;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import com.puresoltechnologies.commons.domain.MetricPrefix;

public class MetricPrefixTest {

    @Test
    public void testToString() {
	for (MetricPrefix fileSize : MetricPrefix.values()) {
	    assertEquals(fileSize.getUnit(), fileSize.toString());
	}
    }

    @Test
    public void testYokto() {
	MetricPrefix yokto = MetricPrefix.YOKTO;
	assertEquals("y", yokto.getUnit());
	assertEquals("Yokto", yokto.getName());
	assertEquals(-24, yokto.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(
		BigDecimal.ONE.divide(thousand).divide(thousand)
			.divide(thousand).divide(thousand).divide(thousand)
			.divide(thousand).divide(thousand).divide(thousand),
		yokto.getFactor());
    }

    @Test
    public void testZepto() {
	MetricPrefix zepto = MetricPrefix.ZEPTO;
	assertEquals("z", zepto.getUnit());
	assertEquals("Zepto", zepto.getName());
	assertEquals(-21, zepto.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(
		BigDecimal.ONE.divide(thousand).divide(thousand)
			.divide(thousand).divide(thousand).divide(thousand)
			.divide(thousand).divide(thousand), zepto.getFactor());
    }

    @Test
    public void testAtto() {
	MetricPrefix atto = MetricPrefix.ATTO;
	assertEquals("a", atto.getUnit());
	assertEquals("Atto", atto.getName());
	assertEquals(-18, atto.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(
		BigDecimal.ONE.divide(thousand).divide(thousand)
			.divide(thousand).divide(thousand).divide(thousand)
			.divide(thousand), atto.getFactor());
    }

    @Test
    public void testFemto() {
	MetricPrefix femto = MetricPrefix.FEMTO;
	assertEquals("f", femto.getUnit());
	assertEquals("Femto", femto.getName());
	assertEquals(-15, femto.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(
		BigDecimal.ONE.divide(thousand).divide(thousand)
			.divide(thousand).divide(thousand).divide(thousand),
		femto.getFactor());
    }

    @Test
    public void testPico() {
	MetricPrefix pico = MetricPrefix.PICO;
	assertEquals("p", pico.getUnit());
	assertEquals("Pico", pico.getName());
	assertEquals(-12, pico.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(
		BigDecimal.ONE.divide(thousand).divide(thousand)
			.divide(thousand).divide(thousand), pico.getFactor());
    }

    @Test
    public void testNano() {
	MetricPrefix nano = MetricPrefix.NANO;
	assertEquals("n", nano.getUnit());
	assertEquals("Nano", nano.getName());
	assertEquals(-9, nano.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(
		BigDecimal.ONE.divide(thousand).divide(thousand)
			.divide(thousand), nano.getFactor());
    }

    @Test
    public void testMicro() {
	MetricPrefix micro = MetricPrefix.MICRO;
	assertEquals("u", micro.getUnit());
	assertEquals("Micro", micro.getName());
	assertEquals(-6, micro.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(BigDecimal.ONE.divide(thousand).divide(thousand),
		micro.getFactor());
    }

    @Test
    public void testMilli() {
	MetricPrefix milli = MetricPrefix.MILLI;
	assertEquals("m", milli.getUnit());
	assertEquals("Milli", milli.getName());
	assertEquals(-3, milli.getDecimalPower());
	BigDecimal thousand = BigDecimal.valueOf(1000.0);
	assertEquals(BigDecimal.ONE.divide(thousand), milli.getFactor());
    }

    @Test
    public void testCenti() {
	MetricPrefix centi = MetricPrefix.CENTI;
	assertEquals("c", centi.getUnit());
	assertEquals("Centi", centi.getName());
	assertEquals(-2, centi.getDecimalPower());
	BigDecimal hundred = BigDecimal.valueOf(100.0);
	assertEquals(BigDecimal.ONE.divide(hundred), centi.getFactor());
    }

    @Test
    public void testDeci() {
	MetricPrefix deci = MetricPrefix.DECI;
	assertEquals("d", deci.getUnit());
	assertEquals("Deci", deci.getName());
	assertEquals(-1, deci.getDecimalPower());
	assertEquals(BigDecimal.ONE.divide(BigDecimal.TEN), deci.getFactor());
    }

    @Test
    public void testOne() {
	MetricPrefix one = MetricPrefix.ONE;
	assertEquals("", one.getUnit());
	assertEquals("", one.getName());
	assertEquals(0, one.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(0), one.getFactor());
    }

    @Test
    public void testDeca() {
	MetricPrefix deca = MetricPrefix.DECA;
	assertEquals("da", deca.getUnit());
	assertEquals("Deca", deca.getName());
	assertEquals(1, deca.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(1), deca.getFactor());
    }

    @Test
    public void testHeco() {
	MetricPrefix hecto = MetricPrefix.HECTO;
	assertEquals("h", hecto.getUnit());
	assertEquals("Hecto", hecto.getName());
	assertEquals(2, hecto.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(2), hecto.getFactor());
    }

    @Test
    public void testKilo() {
	MetricPrefix kilo = MetricPrefix.KILO;
	assertEquals("k", kilo.getUnit());
	assertEquals("Kilo", kilo.getName());
	assertEquals(3, kilo.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(3), kilo.getFactor());
    }

    @Test
    public void testMega() {
	MetricPrefix mega = MetricPrefix.MEGA;
	assertEquals("M", mega.getUnit());
	assertEquals("Mega", mega.getName());
	assertEquals(6, mega.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(6), mega.getFactor());
    }

    @Test
    public void testGiga() {
	MetricPrefix giga = MetricPrefix.GIGA;
	assertEquals("G", giga.getUnit());
	assertEquals("Giga", giga.getName());
	assertEquals(9, giga.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(9), giga.getFactor());
    }

    @Test
    public void testTera() {
	MetricPrefix tera = MetricPrefix.TERA;
	assertEquals("T", tera.getUnit());
	assertEquals("Tera", tera.getName());
	assertEquals(12, tera.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(12), tera.getFactor());
    }

    @Test
    public void testPeta() {
	MetricPrefix peta = MetricPrefix.PETA;
	assertEquals("P", peta.getUnit());
	assertEquals("Peta", peta.getName());
	assertEquals(15, peta.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(15), peta.getFactor());
    }

    @Test
    public void testExa() {
	MetricPrefix exa = MetricPrefix.EXA;
	assertEquals("E", exa.getUnit());
	assertEquals("Exa", exa.getName());
	assertEquals(18, exa.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(18), exa.getFactor());
    }

    @Test
    public void testZetta() {
	MetricPrefix zetta = MetricPrefix.ZETTA;
	assertEquals("Z", zetta.getUnit());
	assertEquals("Zetta", zetta.getName());
	assertEquals(21, zetta.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(21), zetta.getFactor());
    }

    @Test
    public void testYotta() {
	MetricPrefix yotta = MetricPrefix.YOTTA;
	assertEquals("Y", yotta.getUnit());
	assertEquals("Yotta", yotta.getName());
	assertEquals(24, yotta.getDecimalPower());
	assertEquals(BigDecimal.TEN.pow(24), yotta.getFactor());
    }
}
