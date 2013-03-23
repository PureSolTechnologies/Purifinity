package com.puresol.coding.evaluation.api;

import java.io.Serializable;
import java.util.List;

public interface DirectoryResults extends Serializable {

    List<MetricValue> getResults();

}
