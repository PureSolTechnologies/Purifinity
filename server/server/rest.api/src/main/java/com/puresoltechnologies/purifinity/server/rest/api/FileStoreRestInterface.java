package com.puresoltechnologies.purifinity.server.rest.api;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.parsers.source.SourceCode;
import com.puresoltechnologies.purifinity.analysis.domain.CodeAnalysis;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileInformation;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.FileStoreException;

@Path("filestore")
public interface FileStoreRestInterface {

	@PUT
	@Path("files")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Produces(MediaType.APPLICATION_JSON)
	public FileInformation storeRawFile(InputStream rawStream) throws FileStoreException;

	@GET
	@Path("files/{hashId}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public InputStream readRawFile(@PathParam("hashId") HashId hashId) throws FileStoreException;

	@GET
	@Path("files/{hashId}/available")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean isAvailable(@PathParam("hashId") HashId hashId);

	@GET
	@Path("files/{hashId}/sourcecode")
	@Produces(MediaType.APPLICATION_JSON)
	public SourceCode readSourceCode(@PathParam("hashId") HashId hashId) throws FileStoreException;

	@GET
	@Path("files/{hashId}/analyses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CodeAnalysis> loadAnalyses(@PathParam("hashId") HashId hashId) throws FileStoreException;

	@PUT
	@Path("files/{hashId}/analyses")
	@Consumes(MediaType.APPLICATION_JSON)
	public void storeAnalysis(@PathParam("hashId") HashId hashId, CodeAnalysis analysis) throws FileStoreException;

	@GET
	@Path("files/{hashId}/analyzed")
	@Produces(MediaType.APPLICATION_JSON)
	public boolean wasAnalyzed(@PathParam("hashId") HashId hashId) throws FileStoreException;

}
