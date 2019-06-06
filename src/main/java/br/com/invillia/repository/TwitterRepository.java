package br.com.invillia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.invillia.entity.Tweet;

public interface TwitterRepository extends JpaRepository<Tweet, Long>{

	Tweet findByTexto(String string);
	
	@Query("SELECT o FROM Tweet o")
	List<Tweet> getAll();
	
	//List<Tweet> listAllTweets();	
	//List<Tweet> findByProfileName(String name);	
}
