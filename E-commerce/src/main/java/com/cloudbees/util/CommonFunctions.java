package com.cloudbees.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonFunctions {

	public static String printAsJson(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			return obj.toString();
		}
	}

	private CommonFunctions() {
		super();
	}
}
