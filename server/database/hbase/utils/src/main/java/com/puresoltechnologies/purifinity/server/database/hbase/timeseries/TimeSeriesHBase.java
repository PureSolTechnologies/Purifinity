package com.puresoltechnologies.purifinity.server.database.hbase.timeseries;

import java.sql.Connection;

/**
 * TODO create a proxy for time series storage...
 * 
 * @author Rick-Rainer Ludwig
 */
public class TimeSeriesHBase {

    private final Connection connection;

    public TimeSeriesHBase(Connection connection) {
	this.connection = connection;
    }

}
