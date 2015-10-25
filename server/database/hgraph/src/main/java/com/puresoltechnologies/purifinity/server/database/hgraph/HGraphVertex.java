package com.puresoltechnologies.purifinity.server.database.hgraph;

import com.tinkerpop.blueprints.Vertex;

public interface HGraphVertex extends Vertex {

    public Iterable<String> getLabels();

    public void addLabel(String label);

    public void removeLabel(String label);

    public boolean hasLabel(String label);

}
