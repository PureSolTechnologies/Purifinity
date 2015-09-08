package com.puresoltechnologies.purifinity.server.database.hadoop.connector.client;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.database.hadoop.connector.api.BloobServiceRemote;
import com.puresoltechnologies.purifinity.server.wildfly.utils.JndiUtils;

public class BloobService {

    private BloobServiceRemote bloobServiceRemote;

    @PostConstruct
    public void initialize() {
	bloobServiceRemote = JndiUtils.createRemoteEJBInstance(BloobServiceRemote.class, BloobServiceRemote.JNDI_NAME);
    }

    public boolean removeRawFile(HashId hashId) throws IOException {
	return bloobServiceRemote.removeRawFile(hashId);
    }

    public long getFileSize(HashId hashId) throws IOException {
	return bloobServiceRemote.getFileSize(hashId);
    }

    public void storeRawFile(HashId hashId, ByteArrayOutputStream buffer) throws IOException {
	bloobServiceRemote.storeRawFile(hashId, buffer.toByteArray());
    }

    public InputStream readRawFile(HashId hashId) throws IOException {
	return new ByteArrayInputStream(bloobServiceRemote.readRawFile(hashId));
    }

    public boolean isAvailable(HashId hashId) throws IOException {
	return bloobServiceRemote.isAvailable(hashId);
    }

}
