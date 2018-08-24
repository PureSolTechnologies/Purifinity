package com.puresoltechnologies.toolshed.server.impl.dashboards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DashboardDefinition extends DashboardInformation {

    @JsonCreator
    public DashboardDefinition( //
	    @JsonProperty("id") String id, //
	    @JsonProperty("name") String name //
    ) {
	super(id, name);
    }

}
