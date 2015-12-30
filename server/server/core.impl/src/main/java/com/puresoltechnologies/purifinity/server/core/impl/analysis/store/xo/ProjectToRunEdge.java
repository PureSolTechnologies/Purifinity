package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition;
import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Property;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

@EdgeDefinition(DuctileDBElementNames.HAS_ANALYSIS_RUN_LABEL)
public interface ProjectToRunEdge {

    @Outgoing
    public AnalysisProjectVertex getProject();

    @Incoming
    public AnalysisRunVertex getRun();

    @Property(DuctileDBElementNames.ANALYSIS_RUN_ID_PROPERTY)
    public long getRunId();

    public void setRunId(long runId);
}
