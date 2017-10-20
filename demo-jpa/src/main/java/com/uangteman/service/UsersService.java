package com.uangteman.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.uangteman.entity.Users;
import com.uangteman.repo.UsersRepo;

@Service("userService")
@Transactional
public class UsersService implements UserDetailsService {
	@Autowired
	private UsersRepo repo;
	
	public Users register(Users users) throws Exception{
		
		if(repo.findByEmail(users.getEmail())!=null){
			throw new Exception("Email already registered");
		}
		return repo.save(users);
	}
	
	public Users login(String email, String password) throws Exception{
		Users users = repo.findByEmailAndPassword(email, password);
		if(users!=null){
			return users;
		}else{
			throw new Exception("Login fail");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Users users = repo.findByEmail(email);
		if(users==null){
			throw new UsernameNotFoundException("No user present with this email: "+email);
		}else{
			List<String> userRoles = new ArrayList<String>();
			userRoles.add("ACTUATOR");
			return new Users(users, userRoles);
		}		
	}
}
