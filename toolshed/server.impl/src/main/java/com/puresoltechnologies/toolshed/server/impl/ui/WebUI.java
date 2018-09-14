package com.puresoltechnologies.toolshed.server.impl.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.puresoltechnologies.toolshed.server.impl.config.ToolShedServerConfigurationService;

@Path("/")
public class WebUI {

    private final File toolShedHomeDirectory = ToolShedServerConfigurationService.getToolShedHomeDirectory();

    @GET
    @Path("{path:.*}")
    public Response serveUI(@PathParam("path") String path) throws FileNotFoundException, URISyntaxException {
	System.out.println(path);
	if ((path == null) || (path.isEmpty())) {
	    return Response.status(Status.TEMPORARY_REDIRECT).location(new URI("/index.html")).build();
	}
	File file = new File(new File(toolShedHomeDirectory, "src/main/web"), path);
	if (file.isDirectory()) {
	    return Response.status(Status.TEMPORARY_REDIRECT).location(new URI("/index.html")).build();
	}
	String fileName = file.getName();
	MediaType mediaType;
	if (fileName.endsWith(".html")) {
	    mediaType = MediaType.TEXT_HTML_TYPE;
	} else if (fileName.endsWith(".js")) {
	    mediaType = new MediaType("application", "javascript");
	} else if (fileName.endsWith(".css")) {
	    mediaType = new MediaType("text", "css");
	} else if (fileName.endsWith(".png")) {
	    mediaType = new MediaType("image", "png");
	} else if (fileName.endsWith(".jpeg") || fileName.endsWith(".jpg")) {
	    mediaType = new MediaType("image", "jpeg");
	} else if (fileName.endsWith(".gif")) {
	    mediaType = new MediaType("image", "jpeg");
	} else if (fileName.endsWith(".woff")) {
	    mediaType = new MediaType("text", "woff");
	} else if (fileName.endsWith(".woff2")) {
	    mediaType = new MediaType("text", "woff2");
	} else {
	    return Response.status(Status.BAD_REQUEST).build();
	}
	FileInputStream inputStream = new FileInputStream(file);
	return Response.ok().type(mediaType).entity(inputStream).build();
    }

}
