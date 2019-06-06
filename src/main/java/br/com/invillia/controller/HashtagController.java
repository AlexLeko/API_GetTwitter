package br.com.invillia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.invillia.dto.HashtagDTO;
import br.com.invillia.service.HashtagService;


@RestController
public class HashtagController {

	@Autowired
	private HashtagService service;
	
	@GetMapping(path = "/hashtag/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<HashtagDTO>> getAllTweets(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@PostMapping(path = "/hashtag/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<Void> save(@RequestBody HashtagDTO dto) {
		service.save(dto);
		return ResponseEntity.ok().build();
	}
}
