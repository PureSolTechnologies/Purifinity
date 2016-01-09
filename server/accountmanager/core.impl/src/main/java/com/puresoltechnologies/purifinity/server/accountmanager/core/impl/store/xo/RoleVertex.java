package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo;

import java.util.Date;
import java.util.List;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Incoming;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Indexed;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Property;
import com.puresoltechnologies.ductiledb.xo.api.annotation.VertexDefinition;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

/**
 * This is the XO definition of a analysis project vertex in graph database.
 * 
 * @author Rick-Rainer Ludwig
 */
@VertexDefinition(RoleVertex.NAME)
public interface RoleVertex {

    public static final String NAME = "Role";

    @Indexed(unique = true)
    @Property(DuctileDBElementNames.ROLE_ID_PROPERTY)
    public String getId();

    public void setId(String id);

    @Property(DuctileDBElementNames.ROLE_NAME_PROPERTY)
    public String getName();

    public void setName(String name);

    @Property(DuctileDBElementNames.CREATION_TIME_PROPERTY)
    public Date getCreationTime();

    public void setCreationTime(Date time);

    @Incoming
    @BelongsToGroup
    public List<UserVertex> getUsers();

}
