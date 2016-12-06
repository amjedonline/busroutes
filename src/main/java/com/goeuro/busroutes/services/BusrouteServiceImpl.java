package com.goeuro.busroutes.services;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class BusrouteServiceImpl implements BusrouteService {

	private static Map<String, LinkedHashSet<String>> ROUTES = new HashMap<String, LinkedHashSet<String>>();

	@Override
	public void addRoutes(String routeId, List<String> stations) {
		ROUTES.put(routeId, new LinkedHashSet<>(stations));
	}

	@Override
	public boolean checkRoute(final String src, final String dest) {

		// Check all the Routes
		boolean routeFound = ROUTES.keySet().stream().anyMatch(new Predicate<String>() {
			@Override
			public boolean test(String routeId) {

				final LinkedHashSet<String> route = ROUTES.get(routeId);

				// Does the route have src and dest stations
				final List<String> stations = route.stream()
						.filter(stationId -> src.equalsIgnoreCase(stationId) || dest.equalsIgnoreCase(stationId))
						.collect(Collectors.toList());

				// check for the order
				return stations.size() == 2 && src.equalsIgnoreCase(stations.get(0))
						&& dest.equalsIgnoreCase(stations.get(1));
			}
		});

		return routeFound;
	}
}
