package br.com.globoplay.movies.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() {
		try {
			URI uri = new URI("/starwars/movies?id=1");
			mockMvc.perform(MockMvcRequestBuilders
					.get(uri)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().is(200));
			System.out.println("teste executado");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
