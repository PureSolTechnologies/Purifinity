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

    @Property(TitanElementNames.TREE_FS_ELEMENT_HASH)
    public String getHashId();

    public void setHashId(String hashId);

    @Property(TitanElementNames.TREE_ELEMENT_NAME)
    public String getName();

    public void setName(String name);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Property(TitanElementNames.TREE_ELEMENT_SIZE_RECURSIVE)
    public long getSizeRecursive();

    public void setSizeRecursive(long size);

    @Property(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES)
    public long getFileNum();

    public void setFileNum(long fileNum);

    @Property(TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES)
    public long getDirectoryNum();

    public void setDirectoryNum(long directoryNum);

    @Property(TitanElementNames.TREE_ELEMENT_CONTAINS_FILES_RECURSIVE)
    public long getFileNumRecursive();

    public void setFileNumRecursive(long fileNumRecursive);

    @Property(TitanElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE)
    public long getDirectoryNumRecursive();

    public void setDirectoryNumRecursive(long directoryNumRecursive);

    @Outgoing
    @HasContent
    public ContentTreeDirectoryVertex getContent();

    public void setContent(ContentTreeDirectoryVertex content);

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
