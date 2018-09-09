package com.puresoltechnologies.toolshed.client.parts;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.javafx.charts.tree.TreeMapNode;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;
import com.puresoltechnologies.toolshed.client.profiles.graph.CodeGraphEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.InvokesEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.MethodVertex;

public class ProfileTreeAreaChartNode implements TreeMapNode {

    private final Profile profile;
    private final MethodVertex method;
    private final ProfileEntry profileEntry;

    public ProfileTreeAreaChartNode(Profile profile, ProfileEntry profileEntry, MethodVertex method) {
	super();
	this.profile = profile;
	this.profileEntry = profileEntry;
	this.method = method;
    }

    @Override
    public String getName() {
	if (method != null) {
	    return method.getClassName() + ":" + method.getMethodName();
	} else {
	    return "";
	}
    }

    @Override
    public double getValue() {
	return (double) profileEntry.getTotalTime() / (double) profileEntry.getInvocations();
    }

    @Override
    public TreeMapNode getParent() {
	return null;
    }

    @Override
    public List<TreeMapNode> getChildren() {
	List<TreeMapNode> children = new ArrayList<>();
	if (method != null) {
	    for (CodeGraphEdge edge : method.getEdges()) {
		if (edge instanceof InvokesEdge) {
		    InvokesEdge invokes = (InvokesEdge) edge;
		    MethodVertex invokedMethod = (MethodVertex) invokes.getVertices().getSecond();
		    ProfileEntry invokedProfileEntry = profile.findEntry(invokedMethod);
		    if (invokedProfileEntry != null) {
			children.add(new ProfileTreeAreaChartNode(profile, invokedProfileEntry, invokedMethod));
		    }
		}
	    }
	}
	return children;
    }

}
