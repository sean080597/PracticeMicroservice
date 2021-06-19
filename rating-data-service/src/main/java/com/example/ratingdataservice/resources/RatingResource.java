package com.example.ratingdataservice.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ratingdataservice.models.Rating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

	// get all rated movie IDs
	
	// loop movie IDs, call & get details
	
	// put them all together
	
	@GetMapping("/{movieId}")
	public Rating getMovieInfo(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 4);
	}
}
