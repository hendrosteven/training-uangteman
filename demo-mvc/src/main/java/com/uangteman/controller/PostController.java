package com.uangteman.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uangteman.entity.Post;
import com.uangteman.entity.Users;
import com.uangteman.repo.PostRepo;

@Controller
public class PostController {
	
	@Autowired
	private PostRepo repo;
	@Autowired
	private HttpSession session;	

	@RequestMapping("/article")
	public String article(Model model){
		model.addAttribute("article", new Post());
		return "create";
	}
	
	@RequestMapping(value="/article",method=RequestMethod.POST)
	public String articleSave(Post article){
		article.setPublishDate(new Date());
		article.setAuthor((Users)session.getAttribute("user"));
		repo.save(article);
		return "redirect:/";
	}
}
