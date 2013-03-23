package com.puresol.coding.evaluation.api;

import java.io.Serializable;
import java.util.List;

/**
 * @author Rick-Rainer Ludwig
 */
public interface FileResults extends Serializable {

    List<MetricValue> getResults();

}
