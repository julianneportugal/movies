package br.com.globoplay.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.globoplay.exceptions.NotFoundException;

/**
 * Classe de servico para consumo da api swapi
 * 
 * @author Julianne
 *
 */
@Component
public class SwapiService {

	public String get(String uri) throws Exception {
		String response = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpEntity<String> genericResponse = restTemplate.exchange(uri, HttpMethod.GET, null, String.class);
			response = genericResponse.getBody();
		} catch (HttpClientErrorException e) {
			System.out.println("getStatusText: " + e.getStatusText());
			System.out.println("getStatusCode: " + e.getStatusCode());
			if (e.getStatusCode().value() == 404) {
				throw new NotFoundException(e);
			}
		} catch (Exception e) {
			throw e;
		}
		return response;
	}
}
