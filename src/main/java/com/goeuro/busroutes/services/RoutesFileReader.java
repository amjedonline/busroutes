package com.goeuro.busroutes.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class RoutesFileReader {

	public Map<String, List<String>> getRoutesFromClasspath(final String classPathResource) {
		final URL url = Thread.currentThread().getContextClassLoader().getResource(classPathResource);
		if (url == null) {
			throw new RuntimeException("Cannot find resource on classpath: ");
		}

		final String fileName = url.getFile();
		return getRoutesFromAbsolutePath(fileName);
	}

	public Map<String, List<String>> getRoutesFromAbsolutePath(final String fileName) {

		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			// br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());

			if (list.size() < 2) {
				throw new RuntimeException("Routes file is not valid.");
			}

			int count = Integer.valueOf(list.get(0));
			if (count != (list.size() - 1)) {
				throw new RuntimeException("Routes file is not valid.");
			}

			return routesFromRecords(list.subList(1, count));

		} catch (IOException e) {
			throw new RuntimeException("Routes file is not valid.");
		}

	}

	private Map<String, List<String>> routesFromRecords(final List<String> records) {
		final Map<String, List<String>> map = new HashMap<String, List<String>>();

		records.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				final String[] fields = t.split(" ");

				final String busRouteId = fields[0];
				final List<String> busRoute = Arrays.stream(fields).skip(1).collect(Collectors.toList());
				map.put(busRouteId, busRoute);
			}
		});

		return map;

	}
}
