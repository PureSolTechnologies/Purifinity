package com.puresoltechnologies.purifinity.server.core.impl.analysis.store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.database.titan.utils.TitanElementNames;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

public class DirectoryStoreServiceBean implements DirectoryStore {

	@Inject
	private TitanGraph graph;

	@Override
	public boolean isAvailable(HashId hashId) throws DirectoryStoreException {
		Vertex vertex = findDirectory(graph, hashId);
		return vertex != null;
	}

	@Override
	public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
		Vertex vertex = findDirectory(graph, hashId);
		Iterable<Vertex> vertices = vertex.query()
				.has(TitanElementNames.TREE_ELEMENT_IS_FILE, true).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		List<HashId> hashIds = new ArrayList<>();
		while (vertexIterator.hasNext()) {
			Vertex fileVertex = vertexIterator.next();
			hashIds.add(HashId.valueOf((String) fileVertex
					.getProperty(TitanElementNames.TREE_ELEMENT_HASH)));
		}
		return hashIds;
	}

	private Vertex findDirectory(TitanGraph graph, HashId hashId)
			throws DirectoryStoreException {
		Iterable<Vertex> vertices = graph.query()
				.has(TitanElementNames.TREE_ELEMENT_IS_FILE, false)
				.has(TitanElementNames.TREE_ELEMENT_HASH, hashId.toString())
				.vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		if (!vertexIterator.hasNext()) {
			return null;
		}
		Vertex vertex = vertexIterator.next();
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
		Vertex vertex = findDirectory(graph, hashId);
		Iterable<Vertex> vertices = vertex.query()
				.has(TitanElementNames.TREE_ELEMENT_IS_FILE, false).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		List<HashId> hashIds = new ArrayList<>();
		while (vertexIterator.hasNext()) {
			Vertex fileVertex = vertexIterator.next();
			hashIds.add(HashId.valueOf((String) fileVertex
					.getProperty(TitanElementNames.TREE_ELEMENT_HASH)));
		}
		return hashIds;
	}
}
