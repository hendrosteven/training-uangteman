package com.uangteman.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignoutController {

	@Autowired
	private HttpSession session;	
	
	@RequestMapping("/signout")
	public String signout(){
		session.invalidate();
		return "redirect:/";
	}
}
