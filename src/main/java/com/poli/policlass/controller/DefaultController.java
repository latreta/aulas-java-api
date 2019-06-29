package com.poli.policlass.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DefaultController {
	public String index() {
		return "Hello world";
	}
}
