package com.puresoltechnologies.purifinity.client.common.evaluation.views;

import com.puresoltechnologies.purifinity.client.common.branding.Exportable;
import com.puresoltechnologies.purifinity.client.common.branding.Printable;
import com.puresoltechnologies.purifinity.client.common.ui.actions.PartSettingsCapability;
import com.puresoltechnologies.purifinity.client.common.ui.actions.Refreshable;
import com.puresoltechnologies.purifinity.client.common.ui.actions.Reproducable;

public abstract class AbstractMetricViewPart extends AbstractEvaluationView
		implements Reproducable, PartSettingsCapability, Refreshable,
		Exportable, Printable {

}
