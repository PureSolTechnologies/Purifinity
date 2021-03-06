package com.puresoltechnologies.purifinity.server.rest.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.puresoltechnologies.commons.misc.hash.HashId;
import com.puresoltechnologies.purifinity.server.accountmanager.core.api.SupportedRoles;
import com.puresoltechnologies.purifinity.server.common.rest.security.RolesAllowed;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.CommonDirectoryStore;
import com.puresoltechnologies.purifinity.server.core.api.analysis.store.DirectoryStoreException;

@Path("directorystore")
public interface DirectoryStoreRestInterface extends CommonDirectoryStore {

    @Override
    @GET
    @Path("directories/{hashId}/available")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public boolean isAvailable(@PathParam("hashId") HashId hashId) throws DirectoryStoreException;

    @Override
    @GET
    @Path("directories/{hashId}/files")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public List<HashId> getFiles(@PathParam("hashId") HashId hashId) throws DirectoryStoreException;

    @Override
    @GET
    @Path("directories/{hashId}/directories")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed(roles = { SupportedRoles.ENGINEER_ID, SupportedRoles.UNPRIVILEGED_ID })
    public List<HashId> getDirectories(@PathParam("hashId") HashId hashId) throws DirectoryStoreException;
}
