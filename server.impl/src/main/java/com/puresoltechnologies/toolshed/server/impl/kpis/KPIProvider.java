package com.puresoltechnologies.toolshed.server.impl.kpis;

import com.puresoltechnologies.toolshed.server.api.kpis.KPIDefinition;

public interface KPIProvider<K extends KPIDefinition> {

    public K getDefinition();

}
