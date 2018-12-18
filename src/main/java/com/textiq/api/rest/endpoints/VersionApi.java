package com.textiq.api.rest.endpoints;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionApi {

	@GetMapping(path = { "/" }, produces = MediaType.APPLICATION_JSON_VALUE)
	public String version() {
		return "Welcome to EAT project version 1";
	}
}
