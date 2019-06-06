package br.com.invillia.repository;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.invillia.entity.Hashtag;



@ActiveProfiles("test")
@EnableJpaRepositories
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@EntityScan("br.com.invillia.entity")
@SpringBootTest(classes = { HashTagRepository.class })
public class HashTagRepositoryTest {

	@Autowired
	private HashTagRepository repository;
	
	@Before
	public void init() {
		repository.deleteAll();
	}
	
	
	@Test
	public void shouldSaveHashtag() {
		Hashtag hashtag = new Hashtag();
		hashtag.setValue("TDD");
		
		Hashtag save = repository.save(hashtag);
		assertNotNull(save.getId());
	}
	
	@Test
	public void shouldGetAll() {
		Hashtag hashtag = new Hashtag();
		hashtag.setValue("TDD");
		
		Hashtag saved = repository.save(hashtag);
		assertNotNull(saved.getId());		
		
		List<Hashtag> all = repository.findAll();
		assertNotNull(all.size() == 1);
	}
	
	
}
