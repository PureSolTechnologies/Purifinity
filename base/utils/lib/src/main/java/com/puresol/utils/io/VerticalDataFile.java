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

package com.puresol.utils.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.utils.data.TypeWrapper;
import com.puresol.utils.data.VerticalData;

public class VerticalDataFile {

    private static final Logger logger = LoggerFactory
	    .getLogger(VerticalDataFile.class);

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
	    Locale.ENGLISH);
    private VerticalDataFileFormat fileFormat = VerticalDataFileFormat.TAB_SEPARATED;

    public void setDateFormat(DateFormat dateFormat) {
	this.dateFormat = dateFormat;
    }

    public VerticalDataFileFormat getFileFormat() {
	return fileFormat;
    }

    public void setFileFormat(VerticalDataFileFormat fileFormat) {
	this.fileFormat = fileFormat;
    }

    public String readLine(RandomAccessFile f) {
	try {
	    String line;
	    line = f.readLine();
	    if (line != null) {
		if (line.contains("#")) {
		    line = line.substring(0, line.indexOf('#'));
		}
		while (line.endsWith("\n") || line.endsWith("\r")) {
		    line = line.substring(0, line.length() - 1);
		}
	    }
	    return line;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	return null;
    }

    private VerticalData createTable(RandomAccessFile f, String[] headers) {
	int width = headers.length;
	String line = "";
	VerticalData data = new VerticalData();
	ValueType[] types = new ValueType[headers.length];
	for (int index = 0; index < types.length; index++) {
	    types[index] = ValueType
		    .fromClass(TypeWrapper.PRIMITIVE_WRAPPERS[0]);
	}
	while ((line = readLine(f)) != null) {
	    logger.debug(line);
	    String[] splits = line.split("\t");
	    for (int index = 0; index < (width < splits.length ? width
		    : splits.length); index++) {
		ValueType newType = ValueType
			.recognizeFromString(splits[index]);
		if (types[index].compareTo(newType) < 0) {
		    types[index] = newType;
		}
	    }
	}
	for (int index = 0; index < types.length; index++) {
	    data.addColumn(headers[index], types[index]);
	}
	return data;
    }

    public VerticalData read(File file) {
	RandomAccessFile f = null;
	try {
	    VerticalData data = null;
	    f = new RandomAccessFile(file, "r");
	    String line = "";
	    boolean isHeader = true;
	    String[] cols = null;
	    int width = 0;
	    while ((line = readLine(f)) != null) {
		if (line.isEmpty()) {
		    continue;
		}
		String[] splits = line.split("\t");
		if (isHeader) {
		    long pointer = f.getFilePointer();
		    data = createTable(f, splits);
		    f.seek(pointer);
		    isHeader = false;
		    width = data.getColumnNumber();
		    cols = new String[width];
		    continue;
		}
		for (int index = 0; index < width; index++) {
		    if (splits.length > index) {
			cols[index] = splits[index];
		    } else {
			cols[index] = "";
		    }
		}
		data.addRow((Object[]) cols);
	    }
	    f.close();
	    return data;
	} catch (FileNotFoundException e) {
	    logger.error(e.getMessage(), e);
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	if (f != null) {
	    try {
		f.close();
	    } catch (IOException e) {
		// nothing to catch...
	    }
	}
	return null;
    }

    public boolean write(File file, VerticalData data) {
	RandomAccessFile f = null;
	try {
	    f = new RandomAccessFile(file, "rw");
	    String separator = "\t";
	    if (fileFormat == VerticalDataFileFormat.COMMA_SEPARATED) {
		separator = ",";
	    }
	    boolean first = true;
	    for (String columnName : data.getColumnNames()) {
		if (first) {
		    first = false;
		} else {
		    f.writeBytes(separator);
		}
		f.writeBytes(columnName);
	    }
	    f.writeBytes("\n");
	    for (int row = 0; row < data.getRowNumber(); row++) {
		first = true;
		for (int col = 0; col < data.getColumnNumber(); col++) {
		    if (first) {
			first = false;
		    } else {
			f.writeBytes(separator);
		    }
		    if (data.getType(col).getClassObject().equals(Date.class)) {
			f.writeBytes(dateFormat.format(data.getDate(row, col)));
		    } else {
			f.writeBytes(data.getString(row, col));
		    }
		}
		f.writeBytes("\n");
	    }
	    f.close();
	    return true;
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	}
	if (f != null) {
	    try {
		f.close();
	    } catch (IOException e) {
		// nothing to catch here...
	    }
	}
	return false;
    }
}
