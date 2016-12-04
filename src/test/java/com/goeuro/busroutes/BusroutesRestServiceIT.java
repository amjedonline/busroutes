package com.goeuro.busroutes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.goeuro.busroutes.services.BusrouteResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class BusroutesRestServiceIT {

	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void testDirect() {
		final ResponseEntity<BusrouteResponse> entity = restTemplate
				.getForEntity("http://localhost:8080/api/direct?dep_sid=3&arr_sid=6", BusrouteResponse.class);

		Assert.assertTrue(entity.getStatusCode().is2xxSuccessful());

		final BusrouteResponse response = entity.getBody();
		Assert.assertFalse(response.isDirectBusRoute());
	}
}