package com.goeuro.busroutes;

import java.util.List;

public interface BusrouteService {

	public void addRoutes(String routeId, List<String> stations);

	public boolean checkRoute(final String src, final String dest);

}
