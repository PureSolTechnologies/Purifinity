package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.List;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Indexed;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Property;
import com.puresoltechnologies.ductiledb.xo.api.annotation.VertexDefinition;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

@VertexDefinition(ContentTreeFileVertex.NAME)
public interface ContentTreeFileVertex {

    public static final String NAME = "content_tree_file_node";

    @Indexed(unique = true)
    @Property(DuctileDBElementNames.TREE_ELEMENT_HASH)
    public String getHashId();

    public void setHashId(String hashId);

    @Property(DuctileDBElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Incoming
    @HasContent
    public List<FileTreeFileVertex> getFilesFromRuns();

    @Incoming
    @ContainsFile
    public ContentTreeDirectoryVertex getParentDirectory();

}
