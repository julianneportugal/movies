package br.com.globoplay.movies.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.globoplay.exceptions.NotFoundException;
import br.com.globoplay.services.FilmService;
@RestController
@RequestMapping("/starwars/movies")
public class FilmController {

	@Autowired
	FilmService filmService;

	@GetMapping
	public ResponseEntity<?> getFilm(@RequestParam(name="id", required=false) Long idFilm) throws Exception {
		try {
			Object film = filmService.getFilm(idFilm);
			return ResponseEntity.ok(film);
		} catch(NotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			filmService.deleteRepository(id);
			return ResponseEntity.ok().build();
		} catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	
}
