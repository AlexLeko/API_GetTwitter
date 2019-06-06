package br.com.invillia.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.invillia.dto.HashtagDTO;
import br.com.invillia.entity.Hashtag;
import br.com.invillia.repository.HashTagRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashtagService.class)
public class HashtagServiceTest {

	@Autowired
	private HashtagService service;
	
	@MockBean
	private HashTagRepository repository;
	
	@Test
	public void shouldReturnHashtagDTO() {
		Hashtag hashtag = new Hashtag(1L, "TDD");
		BDDMockito.given(repository.findAll())
			.willReturn(Arrays.asList(hashtag));
				
		List<HashtagDTO> list = service.findAll();		
		assertTrue(list.size() == 1);
		
		HashtagDTO dto = list.get(0);
		assertEquals(dto.getHashtag(), "TDD");
	}
	
	@Test
	public void shouldSave() {				
		HashtagDTO hashtagDTO = new HashtagDTO("TDD");		
		service.save(hashtagDTO);	
		
		Mockito.verify(repository, Mockito.atLeast(1))
			.save(new Hashtag(null, "TDD"));		
	}
	
	
}
