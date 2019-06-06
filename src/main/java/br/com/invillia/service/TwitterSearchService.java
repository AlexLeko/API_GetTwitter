package br.com.invillia.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

import br.com.invillia.entity.Hashtag;
import br.com.invillia.repository.HashTagRepository;
import br.com.invillia.repository.TwitterRepository;



@Service
public class TwitterSearchService {

	@Autowired
	private Twitter twitterApi;
	
	@Autowired
	private TwitterRepository repository;
	
	@Autowired
	private HashTagRepository hashtagRepository;
	
	
	@Scheduled(fixedRate = 60000)
	public void getTweets() {
	
		System.out.println("Iniciando JOB");
		
		List<Tweet> tweets = new ArrayList<>(); 
		
		System.out.println("Buscando Tweets");
		hashtagRepository.findAll()
			.forEach(hashtag -> {
				twitterApi.searchOperations()
				.search("#" + hashtag.getValue())
				.getTweets()
				.stream().collect(Collectors.toCollection(() -> tweets));
			});
		
		System.out.println("Busca finalizada: " + tweets.size() + " resultados.");		
		List<br.com.invillia.entity.Tweet> tweetEntitys = new ArrayList<>();	 

		List<br.com.invillia.entity.Tweet> saveds = repository.findAll();
		if(saveds.isEmpty()) {
			tweets.stream()
				.map(tweet -> new br.com.invillia.entity.Tweet(
						tweet.getText(), 
						tweet.getUser().getName(), 
						tweet.getUser().getFollowersCount()))
				.collect(Collectors.toCollection(() -> tweetEntitys));
			 
		} else {
			saveds.forEach(tweetEntity -> {
				tweets.stream()
					.filter(tweet -> !tweet.getText().equals(tweetEntity.getTexto()))
					.map(tweet -> new br.com.invillia.entity.Tweet(
							tweet.getText(), 
							tweet.getUser().getName(), 
							tweet.getUser().getFollowersCount()))
					.collect(Collectors.toCollection(() -> tweetEntitys));
			});
		}
				
		if(!tweetEntitys.isEmpty()) {
			System.out.println("Salvando tweets" + tweets.size());
			repository.saveAll(tweetEntitys);	
		}

	
	
	}	
}
