package com.goeuro.busroutes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.goeuro.busroutes.services.BusrouteService;
import com.goeuro.busroutes.services.BusrouteServiceImpl;
import com.google.common.collect.ImmutableList;

@RunWith(SpringRunner.class)
public class BusrouteServiceImplTest {

	@Test
	public void testCheckRoute() throws Exception {
		BusrouteService service = new BusrouteServiceImpl();
		service.addRoutes("0", ImmutableList.of("0", "1", "2", "3", "4"));
		service.addRoutes("1", ImmutableList.of("3", "1", "6", "5"));
		service.addRoutes("2", ImmutableList.of("2", "0", "6", "4"));

		assertTrue(service.checkRoute("0", "4"));
		assertFalse(service.checkRoute("4", "3"));
	}
}
