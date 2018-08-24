package com.puresoltechnologies.toolshed.server.impl.dashboards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;
import com.puresoltechnologies.toolshed.server.impl.dashboards.DashboardDefinition;

public class DashboardDefinitionTest {

    @Test
    public void testSerialization() throws IOException {
	DashboardDefinition definition = new DashboardDefinition("id", "name");
	String serialized = JsonSerializer.toString(definition);
	DashboardDefinition deserialized = JsonSerializer.fromString(serialized, DashboardDefinition.class);
	assertEquals(definition, deserialized);
    }

}
