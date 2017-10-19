package com.uangteman.controller;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uangteman.dto.LoginForm;
import com.uangteman.dto.Result;
import com.uangteman.entity.Users;
import com.uangteman.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<?> register(@Valid @RequestBody Users users, Errors errors) 
			throws Exception {
		Result result = new Result();
		if(errors.hasErrors()){
			for(ObjectError err: errors.getAllErrors()){
				result.getMessages().add(err.getDefaultMessage());
			}						
			return ResponseEntity.badRequest().body(result);
		}
		Users user =  usersService.register(users);		
		result.setPayload(user);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm, Errors errors) 
			throws Exception{
		
		Result result = new Result();
		
		//return bad request
		if(errors.hasErrors()){
			for(ObjectError err: errors.getAllErrors()){
				result.getMessages().add(err.getDefaultMessage());
			}						
			return ResponseEntity.badRequest().body(result);
		}
		
		Users user =  usersService.login(loginForm.getEmail(), 
				loginForm.getPassword());
		
		result.setPayload(user);
		return ResponseEntity.ok(result);
	}
}
