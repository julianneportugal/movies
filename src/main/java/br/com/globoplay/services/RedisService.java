package br.com.globoplay.services;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
/**
 * Classe para integracao com redis
 * @author Julianne
 *
 */
@Service
public class RedisService {
	
	@Value("${spring.redis.folder}")
	private String folder;
	@Value("${spring.redis.time-to-live}")
	private long expire;

	@Autowired
	StringRedisTemplate redisTemplate;
	
	public void save(String key, String value) {
		this.redisTemplate.opsForValue().set(folder + ":" + key, value, expire, TimeUnit.SECONDS);
	}
	
	public String load(String key) {
		String value = null;
		try {
			key = folder + ":" + key;
			System.out.println("keys: " + this.redisTemplate.keys("movies*"));
			if(this.redisTemplate.hasKey(key)) {
				System.out.println("class: " + this.redisTemplate.opsForValue().get(key).getClass());
				System.out.println(this.redisTemplate.opsForValue().get(key));
				value = this.redisTemplate.opsForValue().get(key);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return value;
	}
	/**
	 * remove do redis
	 * @param key
	 */
	public void delete(String key) {
		this.redisTemplate.delete(folder + ":" + key);
	}
}
