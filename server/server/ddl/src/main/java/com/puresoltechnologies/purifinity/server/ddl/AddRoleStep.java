package com.puresoltechnologies.purifinity.server.ddl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.puresoltechnologies.ductiledb.api.DuctileDBGraph;
import com.puresoltechnologies.genesis.commons.TransformationException;
import com.puresoltechnologies.genesis.transformation.ductiledb.AbstractDuctileDBTransformationStep;
import com.puresoltechnologies.genesis.transformation.ductiledb.DuctileDBTransformationSequence;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

public class AddRoleStep extends AbstractDuctileDBTransformationStep {

    private final SupportedRoles role;

    public AddRoleStep(DuctileDBTransformationSequence sequence, SupportedRoles role, String developer,
	    String comment) {
	super(sequence, developer, "Add role " + role.getName(), comment);
	this.role = role;
    }

    @Override
    public void transform() throws TransformationException {
	DuctileDBGraph graph = getDuctileDBGraph();
	try {
	    Set<String> types = new HashSet<>();
	    types.add("Role");
	    Map<String, Object> properties = new HashMap<>();
	    properties.put(DuctileDBElementNames.ROLE_ID_PROPERTY, role.getId());
	    properties.put(DuctileDBElementNames.ROLE_NAME_PROPERTY, role.getName());
	    properties.put(DuctileDBElementNames.CREATION_TIME_PROPERTY, new Date());
	    graph.addVertex(types, properties);
	    graph.commit();
	} catch (Exception e) {
	    graph.rollback();
	    throw new TransformationException("Could not create default roles in Titan database.", e);
	}
    }

}
