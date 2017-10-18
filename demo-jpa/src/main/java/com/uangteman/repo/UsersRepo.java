package com.uangteman.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uangteman.entity.Users;

public interface UsersRepo extends CrudRepository<Users, Long>{
	
	@Query("select u from Users u where u.email= :email and u.password= :password")
	public Users findByEmailAndPassword(@Param("email") String email,
			@Param("password") String password);
	
	@Query("select u from Users u where u.email= :email")
	public Users findByEmail(@Param("email") String email);
}
