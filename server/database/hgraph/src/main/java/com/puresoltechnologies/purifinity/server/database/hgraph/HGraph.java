package com.puresoltechnologies.purifinity.server.database.hgraph;

import java.io.Closeable;

import org.apache.hadoop.hbase.client.Connection;

import com.tinkerpop.blueprints.TransactionalGraph;

/**
 * This is the central interface for HGraph graphs. It is an extension for
 * TinkerPop's Graph interface to enhance HGraph with functionality not present
 * in the generic graph model.
 * 
 * @author Rick-Rainer Ludwig
 */
public interface HGraph extends TransactionalGraph, Closeable {

    public Connection getConnection();

    public void commit();

    public void rollback();
}
