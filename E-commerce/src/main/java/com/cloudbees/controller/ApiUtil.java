package com.cloudbees.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.NativeWebRequest;

public class ApiUtil {
	private ApiUtil() {
		super();
	}

	public static void setExampleResponse(NativeWebRequest req, String contentType, String example) {
		try {

			HttpServletResponse res = req.getNativeResponse(HttpServletResponse.class);
			if (res != null) {
				res.setCharacterEncoding("UTF-8");
				res.addHeader("Content-Type", contentType);
				res.getWriter().print(example);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}