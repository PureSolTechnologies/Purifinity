package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

@VertexDefinition(FileTreeFileVertex.NAME)
public interface FileTreeFileVertex {

    public static final String NAME = "file_tree_file_node";

    @Property(TitanElementNames.TREE_ELEMENT_NAME)
    public String getName();

    public String setName(String name);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Outgoing
    @HasContent
    public ContentTreeDirectoryVertex getContent();

    @Incoming
    @ContainsFile
    public FileTreeDirectoryVertex getParentDirectory();

}
