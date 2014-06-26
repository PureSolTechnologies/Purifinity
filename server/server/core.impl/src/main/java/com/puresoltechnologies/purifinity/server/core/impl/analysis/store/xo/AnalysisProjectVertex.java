package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.Indexed;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

/**
 * This is the XO definition of a analysis project vertex in graph database.
 * 
 * @author Rick-Rainer Ludwig
 */
@VertexDefinition(AnalysisProjectVertex.NAME)
public interface AnalysisProjectVertex {

    public static final String NAME = "analysis_project";

    @Indexed
    @Property(TitanElementNames.ANALYSIS_PROJECT_UUID_PROPERTY)
    public String getProjectUUID();

    public void setProjectUUID(String uuid);

    @Property(TitanElementNames.CREATION_TIME_PROPERTY)
    public Date getCreationTime();

    public void setCreationTime(Date time);

    public List<ProjectToRunEdge> getAnalysisRuns();

}
