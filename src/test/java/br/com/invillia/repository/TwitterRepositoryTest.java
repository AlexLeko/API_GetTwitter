package br.com.invillia.repository;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.invillia.entity.Tweet;

@EnableJpaRepositories
@EnableAutoConfiguration
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@EntityScan("br.com.invillia.entity")
@SpringBootTest(classes = { TwitterRepository.class })
public class TwitterRepositoryTest {
	
	@Autowired
	private TwitterRepository repository;
	
	@Test
	public void deveriaSalvarTwitter() {		
		Tweet tweet = new Tweet();
		tweet.setTexto("Java é legal");
		
		repository.save(tweet);
		
		Tweet tweet1 = repository.findByTexto("Java é legal");
		assertEquals("Java é legal", tweet1.getTexto());		
	}
	
}
