package com.puresoltechnologies.purifinity.server.core.api.evaluation.defects;

import com.puresoltechnologies.purifinity.evaluation.api.EvaluatorType;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.DirectoryDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.FileDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.GenericDirectoryDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.GenericFileDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.GenericProjectDefects;
import com.puresoltechnologies.purifinity.evaluation.domain.defects.ProjectDefects;
import com.puresoltechnologies.purifinity.server.core.api.evaluation.AbstractEvaluator;
import com.puresoltechnologies.versioning.Version;

public abstract class AbstractDefectEvaluator extends
	AbstractEvaluator<FileDefects, GenericFileDefects, DirectoryDefects, GenericDirectoryDefects, ProjectDefects, GenericProjectDefects> {

    public AbstractDefectEvaluator(String id, String name, Version version, String description) {
	super(id, name, version, EvaluatorType.DEFECTS, description);
    }
}
