package com.puresoltechnologies.purifinity.server.database.hadoop.connector.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Remote;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.common.utils.BuildInformation;

@Remote
public interface BloobServiceRemote {

    public static final String JNDI_NAME = "java:global/database.hadoop.connector.plugin/com-puresoltechnologies-purifinity-server-database.hadoop.connector-"
	    + BuildInformation.getVersion()
	    + "/BloobService!com.puresoltechnologies.purifinity.server.database.hadoop.connector.api.BloobServiceRemote";

    public boolean removeRawFile(HashId hashId) throws IOException;

    public long getFileSize(HashId hashId) throws IOException;

    public void storeRawFile(HashId hashId, ByteArrayOutputStream buffer) throws IOException;

    public InputStream readRawFile(HashId hashId) throws IOException;

    public boolean isAvailable(HashId hashId) throws IOException;

}
