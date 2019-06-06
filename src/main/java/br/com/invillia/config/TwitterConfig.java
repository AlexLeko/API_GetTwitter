package br.com.invillia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.twitter.api.impl.TwitterTemplate;

@Configuration
public class TwitterConfig extends SocialConfigurerAdapter { 

	@Autowired
	private ApplicationConfigProperties configProperties;
	
	@Bean
	@Primary
	public TwitterTemplate twitterTemplate() {		
//		System.out.println("Key: " + configProperties.getConsumerKey() );
//		System.out.println("Token: " + configProperties.getConsumerSecret() );
		
		return new TwitterTemplate(configProperties.getConsumerKey(), configProperties.getConsumerSecret());
	}
	
	@Override
	public UserIdSource getUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}
	
	
	
}
