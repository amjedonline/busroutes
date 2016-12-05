package com.goeuro.busroutes;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.goeuro.busroutes.services.BusrouteService;
import com.goeuro.busroutes.services.RoutesFileReader;

@Component
/**
 * 
 * @author Mohammed Amjed
 * 
 * Initializes the application depending on the active profile.
 * Test environment get the routes loaded from the routes.txt file in src/test/resources directory.
 * While in the production environment we expect file path pointing to the routes we are interested in.  
 *
 */
public class ApplicationRunner implements CommandLineRunner {

	@Autowired
	private Environment environment;

	@Autowired
	private RoutesFileReader routesFileReader;

	@Autowired
	private BusrouteService busrouteService;

	@Override
	public void run(String... args) throws Exception {

		// For test environment let us use the routes.txt file in classpath
		if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
			initFromClasspath("routes.txt");
			return;
		}

		// Else expect a file path in the command line
		if (args == null || args.length != 1) {
			throw new RuntimeException(
					"Cannot initialise application, routes file name provided is either null or ambigous.");
		}

		init(args[0]);

	}

	public void initFromClasspath(final String classPathResource) {
		final URL url = Thread.currentThread().getContextClassLoader().getResource(classPathResource);
		if (url == null) {
			throw new RuntimeException("Cannot find resource on classpath: ");
		}

		final String fileName = url.getFile();
		init(fileName);
	}

	private void init(final String routesFile) {

		final Map<String, List<String>> routes = routesFileReader.getRoutesFrom(routesFile);

		routes.keySet().forEach(new Consumer<String>() {
			@Override
			public void accept(String routeId) {
				busrouteService.addRoutes(routeId, routes.get(routeId));
			}
		});
	}
}
