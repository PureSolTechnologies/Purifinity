package com.puresoltechnologies.toolshed.server.impl.dashboards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.puresoltechnologies.toolshed.commons.JsonSerializer;
import com.puresoltechnologies.toolshed.server.impl.dashboards.DashboardInformation;

public class DashboardInformationTest {

    @Test
    public void testSerialization() throws IOException {
	DashboardInformation definition = new DashboardInformation("id", "name");
	String serialized = JsonSerializer.toString(definition);
	DashboardInformation deserialized = JsonSerializer.fromString(serialized, DashboardInformation.class);
	assertEquals(definition, deserialized);
    }

}
