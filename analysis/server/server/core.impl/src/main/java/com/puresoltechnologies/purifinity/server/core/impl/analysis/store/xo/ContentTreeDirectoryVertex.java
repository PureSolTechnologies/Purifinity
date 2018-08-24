package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.util.List;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Indexed;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Property;
import com.puresoltechnologies.ductiledb.xo.api.annotation.VertexDefinition;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

@VertexDefinition(ContentTreeDirectoryVertex.NAME)
public interface ContentTreeDirectoryVertex {

    public static final String NAME = "content_tree_directory_node";

    @Indexed(unique = true)
    @Property(DuctileDBElementNames.TREE_ELEMENT_HASH)
    public String getHashId();

    public void setHashId(String hashId);

    @Property(DuctileDBElementNames.TREE_ELEMENT_SIZE)
    public long getSize();

    public void setSize(long size);

    @Property(DuctileDBElementNames.TREE_ELEMENT_SIZE_RECURSIVE)
    public long getSizeRecursive();

    public void setSizeRecursive(long size);

    @Property(DuctileDBElementNames.TREE_ELEMENT_CONTAINS_FILES)
    public long getFileNum();

    public void setFileNum(long fileNum);

    @Property(DuctileDBElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES)
    public long getDirectoryNum();

    public void setDirectoryNum(long directoryNum);

    @Property(DuctileDBElementNames.TREE_ELEMENT_CONTAINS_FILES_RECURSIVE)
    public long getFileNumRecursive();

    public void setFileNumRecursive(long fileNumRecursive);

    @Property(DuctileDBElementNames.TREE_ELEMENT_CONTAINS_DIRECTORIES_RECURSIVE)
    public long getDirectoryNumRecursive();

    public void setDirectoryNumRecursive(long directoryNumRecursive);

    @Incoming
    @HasContent
    public List<FileTreeDirectoryVertex> getDirectoriesFromRuns();

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
