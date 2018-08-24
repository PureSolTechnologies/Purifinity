package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.ductiledb.xo.api.annotation.Indexed;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Property;
import com.puresoltechnologies.ductiledb.xo.api.annotation.VertexDefinition;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

/**
 * This is the XO definition of a analysis project vertex in graph database.
 * 
 * @author Rick-Rainer Ludwig
 */
@VertexDefinition(AnalysisProjectVertex.NAME)
public interface AnalysisProjectVertex {

    public static final String NAME = "analysis_project";

    @Indexed(unique = true)
    @Property(DuctileDBElementNames.ANALYSIS_PROJECT_ID_PROPERTY)
    public String getProjectId();

    public void setProjectId(String projectId);

    @Property(DuctileDBElementNames.CREATION_TIME_PROPERTY)
    public Date getCreationTime();

    public void setCreationTime(Date time);

    public List<ProjectToRunEdge> getAnalysisRuns();

}
