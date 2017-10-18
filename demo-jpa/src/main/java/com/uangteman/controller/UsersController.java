package com.uangteman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uangteman.dto.LoginForm;
import com.uangteman.entity.Users;
import com.uangteman.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public Users register(@RequestBody Users users) throws Exception {
		return usersService.register(users);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public Users login(@RequestBody LoginForm loginForm) 
			throws Exception{
		return usersService.login(loginForm.getEmail(), 
				loginForm.getPassword());
	}
}
