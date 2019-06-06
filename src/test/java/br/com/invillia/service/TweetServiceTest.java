package br.com.invillia.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.invillia.dto.TweetDTO;
import br.com.invillia.entity.Tweet;
import br.com.invillia.repository.TwitterRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TweetService.class)
public class TweetServiceTest {

	@Autowired
	private TweetService service;
	
	@MockBean
	private TwitterRepository repository;
	
	@Test
	public void deveriaRetornarTweet() {
		Tweet tweet = new Tweet();
		tweet.setTexto("Java é legal");
		
		BDDMockito.given(repository.getAll()).willReturn(Arrays.asList(tweet));
		
		List<TweetDTO> text = service.getTweet();
		assertEquals("Java é legal", text.get(0).getTweet());
	}
	
}
