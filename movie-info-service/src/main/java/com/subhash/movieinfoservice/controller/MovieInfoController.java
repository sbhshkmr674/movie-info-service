package com.subhash.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.subhash.movieinfoservice.model.Movie;
import com.subhash.movieinfoservice.model.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieInfoController {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/{movieId}")
	public Movie getMovieByMovieId(@PathVariable("movieId") String movieId) {
		
		System.out.println("API Key :"+apiKey);
		
		MovieSummary movieSummary=restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+movieId+"?api_key="+apiKey, MovieSummary.class);
		
		String title=movieSummary.getTitle();
		System.out.println("Title :"+title);
		
		String overview=movieSummary.getOverview();
		System.out.println("Title :"+overview);
		
		return new Movie(movieId,movieSummary.getTitle(),movieSummary.getOverview());
	}
} 
