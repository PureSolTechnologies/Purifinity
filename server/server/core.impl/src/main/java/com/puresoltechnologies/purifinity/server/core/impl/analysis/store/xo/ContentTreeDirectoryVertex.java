package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.List;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

@VertexDefinition(ContentTreeDirectoryVertex.NAME)
public interface ContentTreeDirectoryVertex {

    public static final String NAME = "content_tree_directory_node";

    @Property(TitanElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE)
    public long getSizeRecursive();

    public void setSizeRecursive(long size);

    @Incoming
    @HasContent
    public List<FileTreeFileVertex> getFilesFromRuns();

    @Outgoing
    @ContainsFile
    public List<ContentTreeFileVertex> getFiles();

    @Outgoing
    @ContainsDirectory
    public List<ContentTreeDirectoryVertex> getDirectories();

    @Incoming
    @ContainsDirectory
    public ContentTreeDirectoryVertex getParentDirectory();

}
