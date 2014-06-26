package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.DirectoryStoreService;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeFileVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.TitanXOManager;

public class DirectoryStoreServiceBean implements DirectoryStoreService {

    @Inject
    @TitanXOManager
    private XOManager xoManager;

    @Override
    public boolean isAvailable(HashId hashId) throws DirectoryStoreException {
	return findDirectory(hashId) != null;
    }

    @Override
    public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
	ContentTreeDirectoryVertex directory = findDirectory(hashId);
	List<HashId> hashIds = new ArrayList<>();
	for (ContentTreeFileVertex file : directory.getFiles()) {
	    hashIds.add(HashId.valueOf(file.getHashId()));
	}
	return hashIds;
    }

    private ContentTreeDirectoryVertex findDirectory(HashId hashId)
	    throws DirectoryStoreException {
	ResultIterable<ContentTreeDirectoryVertex> directoryResult = xoManager
		.find(ContentTreeDirectoryVertex.class, hashId.toString());
	Iterator<ContentTreeDirectoryVertex> vertexIterator = directoryResult
		.iterator();
	if (!vertexIterator.hasNext()) {
	    return null;
	}
	ContentTreeDirectoryVertex vertex = vertexIterator.next();
	if (vertexIterator.hasNext()) {
	    throw new DirectoryStoreException(
		    "Found multiple directories with hash '"
			    + hashId.toString()
			    + "'. Database is inconsistent!");
	}
	return vertex;
    }

    @Override
    public List<HashId> getDirectories(HashId hashId)
	    throws DirectoryStoreException {
	ContentTreeDirectoryVertex directory = findDirectory(hashId);
	List<HashId> hashIds = new ArrayList<>();
	for (ContentTreeDirectoryVertex dir : directory.getDirectories()) {
	    hashIds.add(HashId.valueOf(dir.getHashId()));
	}
	return hashIds;
    }
}
