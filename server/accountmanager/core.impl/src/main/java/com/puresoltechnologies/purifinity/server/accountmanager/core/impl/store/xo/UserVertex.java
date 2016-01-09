package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo;

import java.util.Date;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition.Outgoing;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Indexed;
import com.puresoltechnologies.ductiledb.xo.api.annotation.Property;
import com.puresoltechnologies.ductiledb.xo.api.annotation.VertexDefinition;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

/**
 * This is the XO definition of a analysis project vertex in graph database.
 * 
 * @author Rick-Rainer Ludwig
 */
@VertexDefinition(UserVertex.NAME)
public interface UserVertex {

    public static final String NAME = "User";

    @Indexed(unique = true)
    @Property(DuctileDBElementNames.USER_EMAIL_PROPERTY)
    public String getEmail();

    public void setEmail(String email);

    @Property(DuctileDBElementNames.USER_NAME_PROPERTY)
    public String getName();

    public void setName(String name);

    @Property(DuctileDBElementNames.CREATION_TIME_PROPERTY)
    public Date getCreationTime();

    public void setCreationTime(Date time);

    @Outgoing
    @BelongsToGroup
    public RoleVertex getRole();

    public void setRole(RoleVertex role);

}
