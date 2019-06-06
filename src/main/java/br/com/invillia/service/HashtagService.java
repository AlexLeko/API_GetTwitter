package br.com.invillia.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invillia.dto.HashtagDTO;
import br.com.invillia.entity.Hashtag;
import br.com.invillia.repository.HashTagRepository;



@Service
public class HashtagService {

	@Autowired
	private HashTagRepository repository;
	
	public List<HashtagDTO> findAll(){
		return repository.findAll().stream()
				.map(Hashtag::getValue)
				.map(HashtagDTO::new)
				.collect(Collectors.toList());
	}
	
	public void save(HashtagDTO hashtagDTO) {
		repository.save(new Hashtag(null, hashtagDTO.getHashtag()));
	}
	
	
}
