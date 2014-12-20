package com.puresoltechnologies.purifinity.server.analysisservice.rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.misc.HashId;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;

@Path("directorystore")
public interface DirectoryStoreRestInterface extends DirectoryStore {

	@Override
	@GET
	@Path("directories/{hashId}/available")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean isAvailable(@PathParam("hashId") HashId hashId)
			throws DirectoryStoreException;

	@Override
	@GET
	@Path("directories/{hashId}/files")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashId> getFiles(@PathParam("hashId") HashId hashId)
			throws DirectoryStoreException;

	@Override
	@GET
	@Path("directories/{hashId}/directories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<HashId> getDirectories(@PathParam("hashId") HashId hashId)
			throws DirectoryStoreException;
}
