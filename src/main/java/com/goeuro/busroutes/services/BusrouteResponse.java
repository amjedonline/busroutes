package com.goeuro.busroutes.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
/**
 * Response model for Rest service.
 * @author Mohammed Amjed
 *
 */
public class BusrouteResponse {

	@JsonProperty(value = "dep_sid")
	private String source;

	@JsonProperty(value = "arr_sid")
	private String destination;

	@JsonProperty(value = "direct_bus_route")
	private boolean directBusRoute;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public boolean isDirectBusRoute() {
		return directBusRoute;
	}

	public void setDirectBusRoute(boolean directBusRoute) {
		this.directBusRoute = directBusRoute;
	}

}
