package com.puresoltechnologies.toolshed.client.profiles;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import com.puresoltechnologies.streaming.AbstractStreamIterator;
import com.puresoltechnologies.streaming.StreamIterable;
import com.puresoltechnologies.streaming.binary.BinaryInputStream;
import com.puresoltechnologies.streaming.binary.mapper.BinaryMapper;
import com.puresoltechnologies.streaming.binary.mapper.BinaryMappingException;
import com.puresoltechnologies.streaming.streams.OptimizedFileInputStream;

public class RawProfileReader implements Closeable {

    private final File file;
    private final BinaryInputStream binaryInputStream;
    private final BinaryMapper binaryMapper = new BinaryMapper(Charset.defaultCharset());

    public RawProfileReader(File file) throws FileNotFoundException {
	this.file = file;
	binaryInputStream = new BinaryInputStream(new OptimizedFileInputStream(file), LITTLE_ENDIAN);
    }

    @Override
    public void close() throws IOException {
	binaryInputStream.close();
    }

    public StreamIterable<ProfileEntry> iterable() {
	return StreamIterable.of(new AbstractStreamIterator<>() {

	    @Override
	    protected ProfileEntry findNext() {
		try {
		    return binaryMapper.read(binaryInputStream, ProfileEntry.class);
		} catch (BinaryMappingException | IOException e) {
		    return null;
		}
	    }
	});
    }

}
