package br.com.invillia.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invillia.dto.TopProfileDTO;
import br.com.invillia.entity.Tweet;
import br.com.invillia.repository.TwitterRepository;

@Service
public class TweetReportService {
	
	@Autowired
	private TwitterRepository repository;
		
	public List<TopProfileDTO> getTopFive() {
		return repository.findAll().stream()
			.sorted(Comparator.comparing(Tweet::getFollowersCount).reversed())
			.limit(5)
			.map(tweet -> new TopProfileDTO(tweet.getProfileName(), tweet.getFollowersCount()))
			.collect(Collectors.toList());
	}
	
}
