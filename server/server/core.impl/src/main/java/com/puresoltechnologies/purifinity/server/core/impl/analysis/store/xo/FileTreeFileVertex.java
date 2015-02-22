package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.xo.titan.api.annotation.Indexed;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

@VertexDefinition(FileTreeFileVertex.NAME)
public interface FileTreeFileVertex {

    public static final String NAME = "file_tree_file_node";

    @Indexed
    @Property(TitanElementNames.TREE_FS_ELEMENT_HASH)
    public String getHashId();

    public void setHashId(String hashId);

    @Property(TitanElementNames.TREE_ELEMENT_NAME)
    public String getName();

    public void setName(String name);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Property(TitanElementNames.TREE_ELEMENT_SOURCE_CODE_LOCATION)
    public String getSourceLocation();

    public void setSourceLocation(String sourceLocation);

    @Outgoing
    @HasContent
    public ContentTreeFileVertex getContent();

    public void setContent(ContentTreeFileVertex content);

    @Incoming
    @ContainsFile
    public FileTreeDirectoryVertex getParentDirectory();

}
