package com.puresoltechnologies.purifinity.server.core.impl.analysis.store.xo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.puresoltechnologies.xo.titan.api.annotation.EdgeDefinition;

@EdgeDefinition("has_root_directory")
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRootDirectory {
}
