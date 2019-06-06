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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.invillia.dto.TweetDTO;
import br.com.invillia.service.TweetService;

@AutoConfigureMockMvc
@EnableAutoConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TweetController.class)
public class TweetControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private TweetService service;
	
	@Test
	public void shouldReturnTweet() throws Exception {
		BDDMockito.given(service.getTweet()).willReturn(Arrays.asList(new TweetDTO("Java é legal")));
		
		mvc.perform(MockMvcRequestBuilders.get("/tweet/list"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("[0].tweet", Matchers.equalTo("Java é legal")));		
	}

}
