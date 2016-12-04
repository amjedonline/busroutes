package com.goeuro.busroutes;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.google.common.collect.ImmutableList;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// ApplicationContext context = SpringApplication.run(Application.class,
		// args);
		// BusrouteService service = context.getBean(BusrouteService.class);

		BusrouteService service = new BusrouteServiceImpl();
		service.addRoutes("0", ImmutableList.of("0", "1", "2", "3", "4"));
		service.addRoutes("1", ImmutableList.of("3", "1", "6", "5"));
		service.addRoutes("2", ImmutableList.of("2", "0", "6", "4"));

		System.out.println(service.checkRoute("0", "4"));
		System.out.println(service.checkRoute("4", "3"));
	}

	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
