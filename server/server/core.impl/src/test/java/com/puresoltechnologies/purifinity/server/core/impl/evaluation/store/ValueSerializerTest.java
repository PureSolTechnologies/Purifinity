package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.commons.math.GeneralValue;
import com.puresoltechnologies.commons.math.LevelOfMeasurement;
import com.puresoltechnologies.commons.math.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.math.Value;
import com.puresoltechnologies.commons.misc.hash.HashAlgorithm;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.QualityLevel;
import com.puresoltechnologies.purifinity.evaluation.domain.SourceCodeQuality;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.ValueSerializer;

public class ValueSerializerTest {

	@Test
	public void testStringValue() {
		ParameterWithArbitraryUnit<String> parameter = new ParameterWithArbitraryUnit<>(
				"name", "unit", LevelOfMeasurement.ORDINAL, "description",
				String.class);
		Value<String> value = new GeneralValue<String>("value", parameter);
		String serializedValue = ValueSerializer.toString(value);
		Value<?> deserializedValue = ValueSerializer
				.fromString(serializedValue);
		assertEquals(value.getParameter(), deserializedValue.getParameter());
		assertEquals(value.getValue(), deserializedValue.getValue());
	}

	@Test
	public void testIntegerValue() {
		ParameterWithArbitraryUnit<Integer> parameter = new ParameterWithArbitraryUnit<>(
				"name", "unit", LevelOfMeasurement.ORDINAL, "description",
				Integer.class);
		Value<Integer> value = new GeneralValue<Integer>(42, parameter);
		String serializedValue = ValueSerializer.toString(value);
		Value<?> deserializedValue = ValueSerializer
				.fromString(serializedValue);
		assertEquals(value.getParameter(), deserializedValue.getParameter());
		assertEquals(value.getValue(), deserializedValue.getValue());
	}

	@Test
	public void testDoubleValue() {
		ParameterWithArbitraryUnit<Double> parameter = new ParameterWithArbitraryUnit<>(
				"name", "unit", LevelOfMeasurement.ORDINAL, "description",
				Double.class);
		Value<Double> value = new GeneralValue<Double>(1.234e+56, parameter);
		String serializedValue = ValueSerializer.toString(value);
		Value<?> deserializedValue = ValueSerializer
				.fromString(serializedValue);
		assertEquals(value.getParameter(), deserializedValue.getParameter());
		assertEquals(value.getValue(), deserializedValue.getValue());
	}

	@Test
	public void testSourceCodeQualityValue() {
		ParameterWithArbitraryUnit<SourceCodeQuality> parameter = new ParameterWithArbitraryUnit<>(
				"name", "unit", LevelOfMeasurement.ORDINAL, "description",
				SourceCodeQuality.class);
		Value<SourceCodeQuality> value = new GeneralValue<SourceCodeQuality>(
				SourceCodeQuality.MEDIUM, parameter);
		String serializedValue = ValueSerializer.toString(value);
		Value<?> deserializedValue = ValueSerializer
				.fromString(serializedValue);
		assertEquals(value.getParameter(), deserializedValue.getParameter());
		assertEquals(value.getValue(), deserializedValue.getValue());
	}

	@Test
	public void testQualityLevelNullValue() {
		ParameterWithArbitraryUnit<QualityLevel> parameter = new ParameterWithArbitraryUnit<>(
				"name", "unit", LevelOfMeasurement.ORDINAL, "description",
				QualityLevel.class);
		Value<QualityLevel> value = new GeneralValue<QualityLevel>(null,
				parameter);
		String serializedValue = ValueSerializer.toString(value);
		Value<?> deserializedValue = ValueSerializer
				.fromString(serializedValue);
		assertEquals(value.getParameter(), deserializedValue.getParameter());
		assertEquals(value.getValue(), deserializedValue.getValue());
	}

	@Test
	public void testHashIdValue() {
		ParameterWithArbitraryUnit<HashId> parameter = new ParameterWithArbitraryUnit<>(
				"name", "unit", LevelOfMeasurement.ORDINAL, "description",
				HashId.class);
		Value<HashId> value = new GeneralValue<HashId>(new HashId(
				HashAlgorithm.SHA256, "123456"), parameter);
		String serializedValue = ValueSerializer.toString(value);
		Value<?> deserializedValue = ValueSerializer
				.fromString(serializedValue);
		assertEquals(value.getParameter(), deserializedValue.getParameter());
		assertEquals(value.getValue(), deserializedValue.getValue());
	}

}
