package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.puresoltechnologies.ductiledb.xo.api.annotation.EdgeDefinition;

@EdgeDefinition("contains_directory")
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainsDirectory {

}
