package com.puresoltechnologies.purifinity.server.database.neo4j.utils;

import java.io.PrintStream;

import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;

public class Neo4jUtils {

    public static void printVertexInformation(PrintStream printStream, Node node) {
	printStream.println("Vertex: id=" + node.getId());
	printStream.println("==========================");
	printStream.println("Properties:");
	for (String key : node.getPropertyKeys()) {
	    printStream.println("\t" + key + "=" + node.getProperty(key));
	}
	printStream.println("Edges:");
	for (Relationship relationship : node.getRelationships()) {
	    printStream.println("\t" + relationship.getType().name());
	    for (String key : relationship.getPropertyKeys()) {
		printStream.println("\t\t" + key + "=" + relationship.getProperty(key));
	    }
	}
    }

    public static void printEdgeInformation(PrintStream printStream, Relationship relationship) {
	printStream.println("Edge: id=" + relationship.getId());
	printStream.println("==========================");
	printStream.println("Properties:");
	for (String key : relationship.getPropertyKeys()) {
	    printStream.println("\t" + key + "=" + relationship.getProperty(key));
	}
	printStream.println("Vertices:");
	printStream.println("FROM/OUT:");
	printVertexInformation(printStream, relationship.getStartNode());
	printStream.println("TO/IN:");
	printVertexInformation(printStream, relationship.getEndNode());
    }
}
