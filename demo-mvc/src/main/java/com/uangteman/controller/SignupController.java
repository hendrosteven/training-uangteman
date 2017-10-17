package com.uangteman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uangteman.entity.Users;
import com.uangteman.repo.UsersRepo;

@Controller	
public class SignupController {
	
	@Autowired
	private UsersRepo repo;	

	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String singup(Model model){
		model.addAttribute("user", new Users());
		return "signup";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String singupNow(Users user){
		repo.save(user);		
		return "redirect:/";
	}
}
