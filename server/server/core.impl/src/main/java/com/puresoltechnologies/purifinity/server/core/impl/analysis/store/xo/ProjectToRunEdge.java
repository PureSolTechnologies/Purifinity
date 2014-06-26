package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.xo.titan.api.annotation.Property;

@EdgeDefinition(TitanElementNames.HAS_ANALYSIS_RUN_LABEL)
public interface ProjectToRunEdge {

    @Outgoing
    public AnalysisProjectVertex getProject();

    @Incoming
    public AnalysisRunVertex getRun();

    @Property(TitanElementNames.ANALYSIS_RUN_UUID_PROPERTY)
    public String getRunUUID();

    public void setRunUUID(String uuid);
}
