package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.buschmais.xo.api.ResultIterable;
import com.buschmais.xo.api.XOManager;
import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreService;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreServiceRemote;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeDirectoryVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.ContentTreeFileVertex;
import com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo.DuctileDBXOManager;

@Stateless
public class DirectoryStoreServiceBean implements DirectoryStoreService, DirectoryStoreServiceRemote {

    @Inject
    @DuctileDBXOManager
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

    private ContentTreeDirectoryVertex findDirectory(HashId hashId) throws DirectoryStoreException {
	ResultIterable<ContentTreeDirectoryVertex> directoryResult = xoManager.find(ContentTreeDirectoryVertex.class,
		hashId.toString());
	Iterator<ContentTreeDirectoryVertex> vertexIterator = directoryResult.iterator();
	if (!vertexIterator.hasNext()) {
	    return null;
	}
	ContentTreeDirectoryVertex vertex = vertexIterator.next();
	if (vertexIterator.hasNext()) {
	    throw new DirectoryStoreException(
		    "Found multiple directories with hash '" + hashId.toString() + "'. Database is inconsistent!");
	}
	return vertex;
    }

    @Override
    public List<HashId> getDirectories(HashId hashId) throws DirectoryStoreException {
	ContentTreeDirectoryVertex directory = findDirectory(hashId);
	List<HashId> hashIds = new ArrayList<>();
	for (ContentTreeDirectoryVertex dir : directory.getDirectories()) {
	    hashIds.add(HashId.valueOf(dir.getHashId()));
	}
	return hashIds;
    }
}
