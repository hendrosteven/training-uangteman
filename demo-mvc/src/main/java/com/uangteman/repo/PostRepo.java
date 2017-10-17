package com.uangteman.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uangteman.entity.Post;

public interface PostRepo extends CrudRepository<Post, Long>{
	
	@Query("select p from Post p order by p.id desc")
	public List<Post> findAllPost();
	
	@Query("select p from Post p where p.author.id= :authorId order by p.id desc")
	public List<Post> findByAuthorId(@Param("authorId") Long authorId);
	
	@Query("select p from Post p where p.title like :title order by p.id desc")
	public List<Post> findByTitle(@Param("title") String title);
	
	@Query("select p from Post p")
	public List<Post> findWithPageable(Pageable pageable);
}
