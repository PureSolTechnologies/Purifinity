package com.puresoltechnologies.toolshed.client.parts;

import java.util.ArrayList;
import java.util.List;

import com.puresoltechnologies.javafx.charts.tree.TreeAreaChartNode;
import com.puresoltechnologies.toolshed.client.profiles.Profile;
import com.puresoltechnologies.toolshed.client.profiles.ProfileEntry;
import com.puresoltechnologies.toolshed.client.profiles.graph.CodeGraphEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.InvokesEdge;
import com.puresoltechnologies.toolshed.client.profiles.graph.MethodVertex;

public class ProfileTreeAreaChartNode implements TreeAreaChartNode {

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
	return method.getClassName() + ":" + method.getMethodName();
    }

    @Override
    public double getValue() {
	return (double) profileEntry.getTotalTime() / (double) profileEntry.getInvocations();
    }

    @Override
    public TreeAreaChartNode getParent() {
	return null;
    }

    @Override
    public List<TreeAreaChartNode> getChildren() {
	List<TreeAreaChartNode> children = new ArrayList<>();
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
	return children;
    }

}
