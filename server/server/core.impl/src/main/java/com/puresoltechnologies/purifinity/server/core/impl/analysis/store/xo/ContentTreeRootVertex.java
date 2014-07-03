package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

@VertexDefinition(value = ContentTreeRootVertex.NAME, usingIndexedPropertyOf = ContentTreeDirectoryVertex.class)
public interface ContentTreeRootVertex extends ContentTreeDirectoryVertex {

    public static final String NAME = "content_tree_root_node";

    @Incoming
    @HasContentRoot
    public AnalysisRunVertex getAnalysisRun();

}