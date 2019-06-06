package br.com.invillia.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.javafaker.Faker;

import br.com.invillia.entity.Hashtag;
import br.com.invillia.repository.HashTagRepository;
import br.com.invillia.repository.TwitterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TwitterSearchService.class)
public class TwitterSearchServiceTest {

	@Autowired
	private TwitterSearchService service;
	
	@MockBean
	private Twitter twitterApi;
	
	@MockBean
	private TwitterRepository repository;
	
	@MockBean
	private HashTagRepository hashtagRepository;
	
	@MockBean
	private SearchOperations searchOperation;
	
	@Test
	public void shouldSaveNewTweet() {
		Hashtag hashtag = new Hashtag(1L, "TDD");
		
		BDDMockito.given(hashtagRepository.findAll())
			.willReturn(Arrays.asList(hashtag));
		
		BDDMockito.given(twitterApi.searchOperations())
			.willReturn(searchOperation);
		
		BDDMockito.given(searchOperation.search(ArgumentMatchers.anyString()))
			.willReturn(new SearchResults(Arrays.asList(buildTweet()), null));
		
		service.getTweets();
		
		Mockito.verify(repository, Mockito.atLeastOnce())
			.saveAll(ArgumentMatchers.anyList());
	}
		
	@Test
	public void shouldNotDuplicateTweets() {
		Hashtag hashtag = new Hashtag(1L, "TDD");
		
		BDDMockito.given(hashtagRepository.findAll())
			.willReturn(Arrays.asList(hashtag));
		
		BDDMockito.given(twitterApi.searchOperations())
			.willReturn(searchOperation);
		
		BDDMockito.given(searchOperation.search(ArgumentMatchers.anyString()))
			.willReturn(new SearchResults(Arrays.asList(buildTweet()), null));
		
		BDDMockito.given(repository.findAll())
			.willReturn(Arrays.asList(new br.com.invillia.entity.Tweet("TDD Rules")));
		
		service.getTweets();	
		
		Mockito.verify(repository, Mockito.never())
			.saveAll(ArgumentMatchers.anyList());
	}
	
	private Tweet buildTweet() {		
		Faker instance = Faker.instance();
		String fromUser = instance.gameOfThrones().character();
		
		return new Tweet(1L, "TDD Rules", new Date(), fromUser, "", 2L, 2L, "", "");
	}
	
}
