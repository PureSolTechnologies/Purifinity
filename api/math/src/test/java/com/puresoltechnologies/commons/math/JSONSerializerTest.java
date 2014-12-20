package com.puresoltechnologies.commons.math;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;

public class JSONSerializerTest {

	public static class TestClass {
		private String a;
		private String b;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}
	}

	public static class TestValueClass {
		private final String a;
		private final String b;

		public TestValueClass(@JsonProperty("a") String a,
				@JsonProperty("b") String b) {
			super();
			this.a = a;
			this.b = b;
		}

		public String getA() {
			return a;
		}

		public String getB() {
			return b;
		}
	}

	@Test
	public void testToJSON() throws JsonGenerationException,
			JsonMappingException, IOException {
		TestClass test = new TestClass();
		test.setA("A");
		test.setB("B");
		String string = JSONSerializer.toJSONString(test);
		System.out.println(string);
		TestClass o = JSONSerializer.fromJSONString(string, TestClass.class);
		assertEquals(test.getA(), o.getA());
		assertEquals(test.getB(), o.getB());
	}

	@Test
	public void testToJSONWithValueClass() throws JsonGenerationException,
			JsonMappingException, IOException {
		TestValueClass test = new TestValueClass("A", "B");
		String string = JSONSerializer.toJSONString(test);
		System.out.println(string);
		TestValueClass o = JSONSerializer.fromJSONString(string,
				TestValueClass.class);
		assertEquals(test.getA(), o.getA());
		assertEquals(test.getB(), o.getB());
	}
}
