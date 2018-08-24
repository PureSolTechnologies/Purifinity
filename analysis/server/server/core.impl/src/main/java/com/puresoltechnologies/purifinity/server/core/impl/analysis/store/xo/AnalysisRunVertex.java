package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.Date;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Indexed;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Property;
import com.puresoltechnologies.ductiledb.xo.api.annotation.VertexDefinition;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

/**
 * This is the XO definition of a analysis project vertex in graph database.
 * 
 * @author Rick-Rainer Ludwig
 */
@VertexDefinition(AnalysisRunVertex.NAME)
public interface AnalysisRunVertex {

    public static final String NAME = "analysis_run";

    @Indexed(unique = true)
    @Property(DuctileDBElementNames.ANALYSIS_RUN_ID_PROPERTY)
    public Long getRunId();

    public void setRunId(Long runId);

    @Property(DuctileDBElementNames.CREATION_TIME_PROPERTY)
    public Date getCreationTime();

    public void setCreationTime(Date time);

    @Property(DuctileDBElementNames.ANALYSIS_RUN_START_TIME_PROPERTY)
    public Date getStartTime();

    public void setStartTime(Date time);

    @Property(DuctileDBElementNames.ANALYSIS_RUN_DURATION_PROPERTY)
    public long getDuration();

    public void setDuration(long duration);

    @Property(DuctileDBElementNames.ANALYSIS_RUN_DESCRIPTION_PROPERTY)
    public String getDescription();

    public void setDescription(String description);

    public ProjectToRunEdge getAnalysisProject();

    @Outgoing
    @HasRootDirectory
    public FileTreeRootVertex getRootDirectory();

    public void setRootDirectory(FileTreeRootVertex rootDirectory);

    @Outgoing
    @HasContentRoot
    public ContentTreeRootVertex getContentRoot();

    public void setContentRoot(ContentTreeRootVertex contentRoot);
}
