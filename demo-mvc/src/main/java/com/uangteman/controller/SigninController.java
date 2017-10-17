package com.uangteman.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uangteman.dto.Messages;
import com.uangteman.entity.Users;
import com.uangteman.repo.UsersRepo;

@Controller
public class SigninController {

	@Autowired
	private UsersRepo repo;	
	@Autowired
	private HttpSession session;

	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String sigin(Model model){
		model.addAttribute("user", new Users());
		return "signin";
	}
	
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signinNow(Users user){
		Users loginUser = repo
				.findByUserNameAndPassword(user.getUserName(), 
						user.getPassword());
		if(loginUser!=null){
			//session update
			session.setAttribute("user", loginUser);
			return "redirect:/";
		}else{
			Messages msg = new Messages();
			msg.setType(Messages.NotifType.ERROR);
			msg.setMessage("Invalid username or password");
			session.setAttribute("msg", msg);
			return "redirect:/signin";
		}
	}
}
