package com.puresoltechnologies.purifinity.server.core.impl.evaluation.store;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.puresoltechnologies.commons.domain.GeneralValue;
import com.puresoltechnologies.commons.domain.LevelOfMeasurement;
import com.puresoltechnologies.commons.domain.ParameterWithArbitraryUnit;
import com.puresoltechnologies.commons.domain.Value;
import com.puresoltechnologies.commons.misc.hash.HashAlgorithm;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.evaluation.domain.Severity;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.ValueSerializer;

public class ValueSerializerTest {

    @Test
    public void testStringValue() {
	ParameterWithArbitraryUnit<String> parameter = new ParameterWithArbitraryUnit<>("name", "unit",
		LevelOfMeasurement.ORDINAL, "description", String.class);
	Value<String> value = new GeneralValue<String>("value", parameter);
	String serializedValue = ValueSerializer.toString(value);
	Value<?> deserializedValue = ValueSerializer.fromString(serializedValue);
	assertEquals(value.getParameter(), deserializedValue.getParameter());
	assertEquals(value.getValue(), deserializedValue.getValue());
    }

    @Test
    public void testIntegerValue() {
	ParameterWithArbitraryUnit<Integer> parameter = new ParameterWithArbitraryUnit<>("name", "unit",
		LevelOfMeasurement.ORDINAL, "description", Integer.class);
	Value<Integer> value = new GeneralValue<Integer>(42, parameter);
	String serializedValue = ValueSerializer.toString(value);
	Value<?> deserializedValue = ValueSerializer.fromString(serializedValue);
	assertEquals(value.getParameter(), deserializedValue.getParameter());
	assertEquals(value.getValue(), deserializedValue.getValue());
    }

    @Test
    public void testDoubleValue() {
	ParameterWithArbitraryUnit<Double> parameter = new ParameterWithArbitraryUnit<>("name", "unit",
		LevelOfMeasurement.ORDINAL, "description", Double.class);
	Value<Double> value = new GeneralValue<Double>(1.234e+56, parameter);
	String serializedValue = ValueSerializer.toString(value);
	Value<?> deserializedValue = ValueSerializer.fromString(serializedValue);
	assertEquals(value.getParameter(), deserializedValue.getParameter());
	assertEquals(value.getValue(), deserializedValue.getValue());
    }

    @Test
    public void testSourceCodeQualityValue() {
	ParameterWithArbitraryUnit<Severity> parameter = new ParameterWithArbitraryUnit<>("name", "unit",
		LevelOfMeasurement.ORDINAL, "description", Severity.class);
	Value<Severity> value = new GeneralValue<Severity>(Severity.MAJOR, parameter);
	String serializedValue = ValueSerializer.toString(value);
	Value<?> deserializedValue = ValueSerializer.fromString(serializedValue);
	assertEquals(value.getParameter(), deserializedValue.getParameter());
	assertEquals(value.getValue(), deserializedValue.getValue());
    }

    @Test
    public void testHashIdValue() {
	ParameterWithArbitraryUnit<HashId> parameter = new ParameterWithArbitraryUnit<>("name", "unit",
		LevelOfMeasurement.ORDINAL, "description", HashId.class);
	Value<HashId> value = new GeneralValue<HashId>(new HashId(HashAlgorithm.SHA256, "123456"), parameter);
	String serializedValue = ValueSerializer.toString(value);
	Value<?> deserializedValue = ValueSerializer.fromString(serializedValue);
	assertEquals(value.getParameter(), deserializedValue.getParameter());
	assertEquals(value.getValue(), deserializedValue.getValue());
    }

}
