package com.goeuro.busroutes;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.goeuro.busroutes.services.BusrouteResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class BusroutesRestServiceIT {

	private RestTemplate restTemplate;

	private static final String ENDPOINT = "http://localhost:8080/api/direct";

	public BusroutesRestServiceIT() {

		restTemplate = new RestTemplate();
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			protected boolean hasError(org.springframework.http.HttpStatus statusCode) {
				return false;
			}

			@Override
			public boolean hasError(ClientHttpResponse response) throws IOException {
				return false;
			}
		});
	}

	@Test
	public void testDirectRouteNotAvailable() {
		final ResponseEntity<BusrouteResponse> entity = restTemplate.getForEntity(ENDPOINT + "?dep_sid=3&arr_sid=6",
				BusrouteResponse.class);

		Assert.assertTrue(entity.getStatusCode().is2xxSuccessful());

		final BusrouteResponse response = entity.getBody();

		Assert.assertTrue("3".equals(response.getSource()));
		Assert.assertTrue("6".equals(response.getDestination()));
		Assert.assertFalse(response.isDirectBusRoute());
	}

	@Test
	public void testDirectRouteAvailable() {
		final ResponseEntity<BusrouteResponse> entity = restTemplate.getForEntity(ENDPOINT + "?dep_sid=153&arr_sid=12",
				BusrouteResponse.class);

		Assert.assertTrue(entity.getStatusCode().is2xxSuccessful());

		final BusrouteResponse response = entity.getBody();

		Assert.assertTrue("153".equals(response.getSource()));
		Assert.assertTrue("12".equals(response.getDestination()));
		Assert.assertTrue(response.isDirectBusRoute());
	}

	@Test
	public void testForSourceNotGiven() {
		final ResponseEntity<BusrouteResponse> entity = restTemplate.getForEntity(ENDPOINT + "?dep_sid=&arr_sid=12",
				BusrouteResponse.class);

		Assert.assertTrue(entity.getStatusCode().is4xxClientError());

	}

	@Test
	public void testForDestinationNotGiven() {
		final ResponseEntity<BusrouteResponse> entity = restTemplate.getForEntity(ENDPOINT + "?dep_sid=153&arr_sid=",
				BusrouteResponse.class);

		Assert.assertTrue(entity.getStatusCode().is4xxClientError());
	}

	@Test
	public void testForParamsAbsent() {
		final ResponseEntity<BusrouteResponse> entity = restTemplate.getForEntity(ENDPOINT + "?dep_sid=&arr_sid=",
				BusrouteResponse.class);

		Assert.assertTrue(entity.getStatusCode().is4xxClientError());

	}
}