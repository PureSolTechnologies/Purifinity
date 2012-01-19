/***************************************************************************
 *
 * Copyright 2009-2010 PureSol Technologies 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 *
 ***************************************************************************/

package com.puresol.gui.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a slightly modified GregorianCalendar to support the TASK notation
 * for dates and times. For details have a look into the TASK manual. In short
 * the formats are:<br/>
 * <br/>
 * date: <tt>yyyy-mm-dd</tt><br/>
 * time: <tt>hh:mm:ss</tt><br/>
 * full: <tt>yyyy-mm-dd hh:mm:ss</tt><br/>
 * 
 * @author Rick-Rainer Ludwig
 */
public class Time extends GregorianCalendar {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(Time.class);

    /**
     * This is the standard constructor without any arguments.
     */
    public Time() {
	super();
    }

    /**
     * This method returns the current time as Date.
     * 
     * @return A Date object is returned containing the current time.
     */
    static public Date now() {
	return new Date();
    }

    /**
     * This method returns the current date and time in a customized output
     * format specified by a SimpleDateFormat format string.
     * 
     * @see java.text.SimpleDateFormat
     * @param format
     *            is the SimpleDateFormat format string.
     * @return A String is returned containing the customized current date and
     *         time information.
     */
    static public String getNowString(String format) {
	return new SimpleDateFormat(format).format(now());
    }

    /**
     * This method returns the specified date and time in a customized output
     * format specified by a SimpleDateFormat format string.
     * 
     * @see java.text.SimpleDateFormat
     * @param format
     *            is the SimpleDateFormat format string.
     * @return A String is returned containing the customized current date and
     *         time information.
     */
    static private String getDateString(Date date, String format) {
	if (date == null) {
	    return "";
	}
	return new SimpleDateFormat(format).format(date);
    }

    /**
     * This method reads the current date and returns the result in TASK format.
     * 
     * @return A string is returned representing the current date in yyyy-mm-dd
     *         format.
     */
    static public String getDateString() {
	return getNowString("yyyy-MM-dd");
    }

    /**
     * This method reads the current time and returns the result in TASK format.
     * 
     * @return A string is returned representing the current time in hh-mm-ss
     *         format.
     */
    static public String getTimeString() {
	return getNowString("HH:mm:ss");
    }

    /**
     * This method returns the current date and(!) time in the common format
     * "yyyy-mm-dd hh:mm:ss".
     * 
     * @return A String is returned representing the current date and time in
     *         the format, given above.
     */
    static public String getFullTimeString() {
	return getNowString("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * This method converts Date objects into a generalized date strings.
     * 
     * @param date
     *            is a Date object containing date information to be converted.
     * @return A String is returned with the converted date string.
     */
    static public String date2DateString(Date date) {
	return getDateString(date, "yyyy-MM-dd");
    }

    /**
     * This method converts Date objects into a generalized time strings.
     * 
     * @param date
     *            is a Date object containing date information to be converted.
     * @return A String is returned with the converted time string.
     */
    static public String date2TimeString(Date date) {
	return getDateString(date, "HH:mm:ss");
    }

    /**
     * This method converts Date objects into a generalized date and time
     * strings.
     * 
     * @param date
     *            is a Date object containing date information to be converted.
     * @return A String is returned with the converted date and time string.
     */
    static public String date2String(Date date) {
	return getDateString(date, "yyyy-MM-dd HH:mm:ss");
    }

    static public Date string2Date(String dateString) {
	if (dateString == null) {
	    return null;
	}
	if (dateString.isEmpty()) {
	    return null;
	}
	try {
	    String format = "yyyy-MM-dd HH:mm:ss";
	    format = format.substring(0, dateString.length() - 1);
	    return new SimpleDateFormat(format).parse(dateString);
	} catch (ParseException e) {
	    logger.error(e.getMessage(), e);
	    return null;
	}
    }
}
