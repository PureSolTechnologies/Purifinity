package com.puresoltechnologies.purifinity.framework.store.db.analysis;

import java.io.File;
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

	private static final String FILES_FILE = "files.persist";
	private static final String DIRECTORIES_FILE = "directories.persist";

	@Override
	public boolean isAvailable(HashId hashId) {
		Session session = CassandraConnection.getAnalysisSession();
		ResultSet resultSet = session
				.execute("SELECT hashid FROM files WHERE hashid="
						+ hashId.getHash());
		return resultSet.one() != null;
	}

	@Override
	public List<HashId> getFiles(HashId hashId) throws DirectoryStoreException {
		TitanGraph graph = TitanConnection.getGraph();
		Vertex vertex = findTreeElement(graph, hashId);
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

	private Vertex findTreeElement(TitanGraph graph, HashId hashId)
			throws DirectoryStoreException {
		Iterable<Vertex> vertices = graph.query()
				.has(TitanConnection.TREE_ELEMENT_IS_FILE, false)
				.has(TitanConnection.TREE_ELEMENT_HASH, hashId.getHash())
				.vertices();
		Iterator<Vertex> vertexIterator = vertices.iterator();
		if (!vertexIterator.hasNext()) {
			return null;
		}
		Vertex vertex = vertexIterator.next();
		if (vertexIterator.hasNext()) {
			throw new DirectoryStoreException(
					"Found multiple directories with hash '" + hashId.getHash()
							+ "'. Database is inconsistent!");
		}
		return vertex;
	}

	@Override
	public List<HashId> getDirectories(HashId hashId)
			throws DirectoryStoreException {
		return restore(new File(getDirectoryStoreDirectory(hashId),
				DIRECTORIES_FILE));
	}
}
