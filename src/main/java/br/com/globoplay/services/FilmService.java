package br.com.globoplay.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import br.com.globoplay.models.Film;
import br.com.globoplay.models.FilmDto;
import br.com.globoplay.models.FilmsAll;

@Service
public class FilmService {

	@Autowired
	SwapiService swapiService;
	@Autowired
	RedisService redisService;
	@Autowired
	Gson gson;
	@Value("${swapi.uri}")
	private String uri;

	public Object getFilm(Long idFilm) throws Exception {
		FilmDto film = null;
		try {
			if (idFilm == null) {
				FilmsAll filmsAll = gson.fromJson(swapiService.get(this.uri), FilmsAll.class);
				List<FilmDto> filmList = new ArrayList<>();
				Long id = 1l;
				for (Film filmResult : filmsAll.getResults()) {
					FilmDto filmDto = this.convert(id, filmResult);
					filmList.add(filmDto);
					id++;
				}
				return filmList;
			}
			film = this.convert(idFilm);
			return film;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	/**
	 * remove do redis
	 * @param idFilm
	 */
	public void deleteRepository(Long idFilm) {
		this.redisService.delete(idFilm.toString());
	}
	
	public FilmDto convert(Long idFilm) throws Exception {
		return this.convert(idFilm,null);
	}

	public FilmDto convert(Long idFilm, Film filmResponse) throws Exception {
		FilmDto film = null;
		try {
			JsonParser jsonParser = new JsonParser();
			String filmSwapi = this.redisService.load(idFilm.toString());
			if (filmSwapi != null) {
				film = gson.fromJson(filmSwapi, FilmDto.class);
				return film;
			}
			filmSwapi = swapiService.get(this.uri + idFilm + "/");
			if(filmResponse == null) {
				filmResponse = gson.fromJson(filmSwapi, Film.class);
			}
			film = new FilmDto(idFilm, filmResponse.getTitle(), filmResponse.getOpening_crawl(),
					filmResponse.getDirector(), filmResponse.getProducer(), filmResponse.getRelease_date(), null);
			System.out.println("id: " + idFilm);
			System.out.println(film.toString());
			List<String> charactersList = new ArrayList<String>();
			for (String url : filmResponse.getCharacters()) {
				System.out.println("url peaple: " + url);
				String c = swapiService.get(url.replace("http", "https"));
				System.out.println("character response: " + c);
				JsonObject character = jsonParser.parse(c).getAsJsonObject();
				System.out.println("character: " + character.toString());
				charactersList.add(character.get("name").getAsString());
			}
			film.setCharacters(charactersList);
			filmSwapi = gson.toJson(film, FilmDto.class);
			System.out.println("filmSwapi: " + filmSwapi);
			this.redisService.save(idFilm.toString(), filmSwapi);
			return film;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
