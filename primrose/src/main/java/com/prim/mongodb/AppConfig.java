package com.prim.mongodb;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
 
import com.mongodb.MongoClient;

@Configuration
public class AppConfig {
	@Bean
	public MongoDbFactory mongoDbFactory() throws UnknownHostException{
		return new SimpleMongoDbFactory(new MongoClient("192.168.1.228", 27017), "test");
	}
	
	@Bean
	public MongoOperations mongoOperations() throws UnknownHostException{
		return new MongoTemplate(mongoDbFactory());
	}
}
