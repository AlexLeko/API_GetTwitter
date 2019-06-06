package br.com.invillia.controller;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.invillia.dto.HashtagDTO;
import br.com.invillia.dto.TweetDTO;
import br.com.invillia.service.HashtagService;
import br.com.invillia.service.TweetService;

@AutoConfigureMockMvc
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HashtagController.class)
public class HashtagControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private HashtagService service;
	
	@Test
	public void shouldReturnHashtags() throws Exception {
		BDDMockito.given(service.findAll())
			.willReturn(Arrays.asList(new HashtagDTO("Java é legal")));
		
		mvc.perform(MockMvcRequestBuilders.get("/hashtag/list"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("[0].hashtag", Matchers.equalTo("Java é legal")));		
	}
	
	@Test
	public void shouldSaveHashtag() throws Exception {
		HashtagDTO hashDTO = new HashtagDTO("TDD");
		
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(hashDTO);
		
		mvc.perform(MockMvcRequestBuilders.post("/hashtag/save")
			.content(json).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk());	
	}

}
