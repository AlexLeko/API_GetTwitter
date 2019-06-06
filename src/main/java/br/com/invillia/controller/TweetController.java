package br.com.invillia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invillia.dto.TopProfileDTO;
import br.com.invillia.dto.TweetDTO;
import br.com.invillia.service.TweetReportService;
import br.com.invillia.service.TweetService;

@RestController
public class TweetController {

	@Autowired
	private TweetService service;
	
	@Autowired
	private TweetReportService reporterService;
	
	@GetMapping(path = "/tweet/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<TweetDTO>> getAllTweets(){
		return ResponseEntity.ok(service.getTweet());
	}
	
	@GetMapping(path = "/tweet/topfive", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<TopProfileDTO>> getTopFiveTweets(){
		return ResponseEntity.ok(reporterService.getTopFive());	
	}
	
	
	
}
