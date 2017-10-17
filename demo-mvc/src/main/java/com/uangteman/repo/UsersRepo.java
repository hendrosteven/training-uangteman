package com.uangteman.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uangteman.entity.Users;

public interface UsersRepo extends CrudRepository<Users, Long>{
	
	@Query("select u from Users u where u.userName= :user and u.password= :pass")
	public Users findByUserNameAndPassword(@Param("user") String user,
			@Param("pass") String pass);
	
	
}
