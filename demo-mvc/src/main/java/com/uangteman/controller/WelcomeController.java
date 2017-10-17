package com.uangteman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uangteman.repo.PostRepo;

@Controller
public class WelcomeController {

	@Autowired
	private PostRepo postRepo;
	
	@RequestMapping("/")
	public String welcome(Model model){
		model.addAttribute("top5",postRepo
				.findWithPageable(new PageRequest(0, 5, Direction.DESC, "id")));
		model.addAttribute("posts", postRepo.findAllPost());
		return "index";
	}
}
