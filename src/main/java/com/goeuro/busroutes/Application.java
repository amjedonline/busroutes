package com.goeuro.busroutes;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.goeuro.busroutes.services.BusrouteService;
import com.goeuro.busroutes.services.RoutesFileReader;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		final ApplicationContext context = SpringApplication.run(Application.class, args);
		final BusrouteService routeService = context.getBean(BusrouteService.class);

		final RoutesFileReader fileReader = context.getBean(RoutesFileReader.class);
		final Map<String, List<String>> routes = fileReader.getRoutesFromClasspath("routes.txt");

		routes.keySet().forEach(new Consumer<String>() {
			@Override
			public void accept(String routeId) {
				routeService.addRoutes(routeId, routes.get(routeId));
			}
		});
	}

}
