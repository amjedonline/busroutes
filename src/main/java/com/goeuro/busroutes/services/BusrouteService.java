package com.goeuro.busroutes.services;

import java.util.List;

/**
 * 
 * @author Mohammed Amjed
 * This is all in effect the Busouteservice does.
 * <ul>
 * 	<li>Add new routes.</li>
 * 	<li>Check availability of direct routes.</li>
 * </ul>
 *
 */
public interface BusrouteService {

	void addRoutes(String routeId, List<String> stations);

	boolean checkRoute(String src, String dest);

}
