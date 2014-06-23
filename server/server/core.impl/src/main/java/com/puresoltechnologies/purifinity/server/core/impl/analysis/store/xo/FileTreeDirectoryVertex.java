package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.List;

import com.puresoltechnologies.purifinity.server.database.titan.TitanElementNames;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.xo.titan.api.annotation.Property;
import com.puresoltechnologies.xo.titan.api.annotation.VertexDefinition;

@VertexDefinition(FileTreeDirectoryVertex.NAME)
public interface FileTreeDirectoryVertex {

    public static final String NAME = "file_tree_directory_node";

    @Property(TitanElementNames.TREE_ELEMENT_NAME)
    public String getName();

    public String setName(String name);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE)
    public long getSizeRecursive();

    public void setSizeRecursive(long size);

    @Outgoing
    @HasContent
    public ContentTreeDirectoryVertex getContent();

    @Outgoing
    @ContainsFile
    public List<FileTreeFileVertex> getFiles();

    @Outgoing
    @ContainsDirectory
    public List<FileTreeDirectoryVertex> getDirectories();

    @Incoming
    @ContainsDirectory
    public FileTreeDirectoryVertex getParentDirectory();
}
