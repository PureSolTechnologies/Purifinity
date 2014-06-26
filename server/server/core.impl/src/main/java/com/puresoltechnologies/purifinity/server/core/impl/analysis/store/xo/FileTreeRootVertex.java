package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

@VertexDefinition(value = FileTreeRootVertex.NAME, usingIndexedPropertyOf = FileTreeDirectoryVertex.class)
public interface FileTreeRootVertex extends FileTreeDirectoryVertex {

    public static final String NAME = "file_tree_root_node";

    @Incoming
    @HasRootDirectory
    public AnalysisRunVertex getAnalysisRun();

}
