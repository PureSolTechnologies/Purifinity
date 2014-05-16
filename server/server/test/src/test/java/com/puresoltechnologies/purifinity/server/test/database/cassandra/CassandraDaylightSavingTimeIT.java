package com.puresoltechnologies.purifinity.server.test.database.cassandra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GregorianChronology;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.puresoltechnologies.purifinity.server.test.AbstractPurifinityServerServerTest;

public class CassandraDaylightSavingTimeIT extends
		AbstractPurifinityServerServerTest {

	@Inject
	private Cluster cluster;

	private Session session = null;
	private final String KEYSPACE_NAME = getClass().getSimpleName();

	@Before
	public void createKeyspace() {
		try (Session systemSession = cluster.connect()) {
			systemSession
					.execute("CREATE KEYSPACE IF NOT EXISTS "
							+ KEYSPACE_NAME
							+ " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
			systemSession.execute("CREATE TABLE " + KEYSPACE_NAME
					+ ".dsttest (time timestamp PRIMARY KEY);");
			session = cluster.connect(KEYSPACE_NAME);
		} catch (Exception e) {
			e.printStackTrace();
			fail("Could not initialize test.");
		}
	}

	@After
	public void removeKeyspace() {
		if (session != null) {
			session.close();
			session = null;
		}
		try (Session systemSession = cluster.connect()) {
			systemSession.execute("DROP KEYSPACE " + KEYSPACE_NAME + ";");
		}
	}

	@Before
	public void checkPreconditions() {
		assertNotNull(cluster);
		assertNotNull(cluster.isClosed());
	}

	@Test
	public void testSummerToWinter2014() {
		// On October 26th 3:00 -> 2:00
		DateTimeZone timeZone = DateTimeZone.forID("Europe/Berlin");
		Chronology chronology = GregorianChronology.getInstance(timeZone);
		DateTime startTime = new DateTime(2014, 10, 26, 1, 59, 59, 0,
				chronology);
		PreparedStatement preparedStatement = session
				.prepare("INSERT INTO dsttest (time) VALUES (?)");
		List<DateTime> dateTimes = new ArrayList<>();
		for (int i = 0; i < 3602; i++) {
			DateTime dt = startTime.plusSeconds(i);
			dateTimes.add(dt);
			BoundStatement boundStatement = preparedStatement.bind(dt.toDate());
			session.execute(boundStatement);
		}
		List<Date> dates = new ArrayList<>();
		ResultSet resultSet = session.execute("SELECT time FROM dsttest;");
		while (!resultSet.isExhausted()) {
			Row row = resultSet.one();
			dates.add(row.getDate("time"));
			System.err.println(row.getDate("time"));
		}
		Collections.sort(dateTimes, new Comparator<DateTime>() {

			@Override
			public int compare(DateTime o1, DateTime o2) {
				return o1.compareTo(o2);
			}
		});
		Collections.sort(dates);
		assertEquals(dateTimes.size(), dates.size());
		for (int i = 0; i < dateTimes.size(); i++) {
			DateTime time = dateTimes.get(i);
			Date date = dates.get(i);
			assertEquals(time.toDate(), date);
		}
	}

	@Test
	public void testWinterToSummer2014() {
		// On March 30th 2:00 -> 3:00
		DateTimeZone timeZone = DateTimeZone.forID("Europe/Berlin");
		Chronology chronology = GregorianChronology.getInstance(timeZone);
		DateTime startTime = new DateTime(2014, 03, 30, 1, 59, 59, 0,
				chronology);
		PreparedStatement preparedStatement = session
				.prepare("INSERT INTO dsttest (time) VALUES (?)");
		List<DateTime> dateTimes = new ArrayList<>();
		for (int i = 0; i < 3602; i++) {
			DateTime dt = startTime.plusSeconds(i);
			dateTimes.add(dt);
			BoundStatement boundStatement = preparedStatement.bind(dt.toDate());
			session.execute(boundStatement);
		}
		List<Date> dates = new ArrayList<>();
		ResultSet resultSet = session.execute("SELECT time FROM dsttest;");
		while (!resultSet.isExhausted()) {
			Row row = resultSet.one();
			dates.add(row.getDate("time"));
			System.err.println(row.getDate("time"));
		}
		Collections.sort(dateTimes, new Comparator<DateTime>() {

			@Override
			public int compare(DateTime o1, DateTime o2) {
				return o1.compareTo(o2);
			}
		});
		Collections.sort(dates);
		assertEquals(dateTimes.size(), dates.size());
		for (int i = 0; i < dateTimes.size(); i++) {
			DateTime time = dateTimes.get(i);
			Date date = dates.get(i);
			assertEquals(time.toDate(), date);
		}
	}
}
