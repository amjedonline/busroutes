package com.goeuro.busroutes.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.goeuro.busroutes.services.BusrouteResponse;
import com.goeuro.busroutes.services.BusrouteService;

/**
 * 
 * @author Mohammed Amjed
 *
 */
@Component
@Path("/api")
public class BusroutesRestService {

	@Autowired
	private BusrouteService service;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/direct")
	public Response direct(@QueryParam("dep_sid") final String source,
			@QueryParam("arr_sid") final String destination) {

		if (StringUtils.isEmpty(source) || StringUtils.isEmpty(destination)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		final BusrouteResponse response = new BusrouteResponse();
		response.setSource(source);
		response.setDestination(destination);

		boolean isDirectRouteAvailable = service.checkRoute(source, destination);
		response.setDirectBusRoute(isDirectRouteAvailable);

		return Response.ok().entity(response).build();
	}

}