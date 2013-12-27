package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStore;
import com.puresoltechnologies.purifinity.framework.store.api.DirectoryStoreException;
import com.puresoltechnologies.purifinity.framework.store.db.CassandraConnection;
import com.puresoltechnologies.purifinity.framework.store.db.TitanConnection;
import com.thinkaurelius.titan.core.TitanGraph;
import com.tinkerpop.blueprints.Vertex;

public class DirectoryStoreImpl implements DirectoryStore {

	@Override
	public boolean isAvailable(HashId hashId) {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session
				.execute("SELECT hashid FROM files WHERE hashid="
						+ hashId.toString());
		return resultSet.one() != null;
	}

	@Override
	public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = findDirectory(graph, hashId);
		Iterable<Vertex> vertices = vertex.query()
				.has(TitanConnection.TREE_ELEMENT_IS_FILE, true).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		List<HashId> hashIds = new ArrayList<>();
		while (vertexIterator.hasNext()) {
			Vertex fileVertex = vertexIterator.next();
			hashIds.add(HashId.fromString((String) fileVertex
					.getProperty(TitanConnection.TREE_ELEMENT_HASH)));
		}
		return hashIds;
	}

	private Vertex findDirectory(TitanGraph graph, HashId hashId)
			throws DirectoryStoreException {
		Iterable<Vertex> vertices = graph.query()
				.has(TitanConnection.TREE_ELEMENT_IS_FILE, false)
				.has(TitanConnection.TREE_ELEMENT_HASH, hashId.toString())
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
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = findDirectory(graph, hashId);
		Iterable<Vertex> vertices = vertex.query()
				.has(TitanConnection.TREE_ELEMENT_IS_FILE, false).vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		List<HashId> hashIds = new ArrayList<>();
		while (vertexIterator.hasNext()) {
			Vertex fileVertex = vertexIterator.next();
			hashIds.add(HashId.fromString((String) fileVertex
					.getProperty(TitanConnection.TREE_ELEMENT_HASH)));
		}
		return hashIds;
	}
}
