package br.com.globoplay.movies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@ComponentScan({ "br.com.globoplay.*" })
@Configuration
@SpringBootApplication
@EnableCaching
public class MoviesApplication {
	
	@Value("${spring.redis.host}")
	private String redisHost;

	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(redisHost);
        return factory;
	}
	 
	@Bean
	public StringRedisTemplate redisTemplate() {
		StringRedisTemplate template = new StringRedisTemplate();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}	

}
