package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.List;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

@VertexDefinition(ContentTreeFileVertex.NAME)
public interface ContentTreeFileVertex {

    public static final String NAME = "content_tree_file_node";

    @Property(TitanElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE)
    public long getSizeRecursive();

    public void setSizeRecursive(long size);

    @Incoming
    @HasContent
    public List<FileTreeFileVertex> getFiles();

    @Incoming
    @ContainsFile
    public ContentTreeDirectoryVertex getParentDirectory();

}
