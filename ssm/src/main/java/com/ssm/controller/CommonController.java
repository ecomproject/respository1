package com.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {

	@RequestMapping(value = "/getTree", method = RequestMethod.GET)
	public String findUser() {
		return "/tree";
	}
}
