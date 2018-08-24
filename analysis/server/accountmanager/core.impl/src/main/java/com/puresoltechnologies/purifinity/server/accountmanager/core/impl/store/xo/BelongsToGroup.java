package com.puresoltechnologies.purifinity.server.accountmanager.core.impl.store.xo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition;
import com.puresoltechnologies.purifinity.server.database.ductiledb.utils.DuctileDBElementNames;

@EdgeDefinition(DuctileDBElementNames.BELONGS_TO_GROUP_LABEL)
@Retention(RetentionPolicy.RUNTIME)
public @interface BelongsToGroup {
}
