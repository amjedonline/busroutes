
package com.goeuro.busroutes;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.goeuro.busroutes.ws.BusroutesRestService;

/**
 * 
 * @author Mohammed Amjed
 *
 */
@Configuration
public class JerseyConfiguration extends ResourceConfig {
	public JerseyConfiguration() {

		register(BusroutesRestService.class);

	}
}