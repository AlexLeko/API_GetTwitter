package br.com.invillia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import br.com.invillia.entity.Hashtag;

public interface HashTagRepository extends JpaRepository<Hashtag, Long>{

}
