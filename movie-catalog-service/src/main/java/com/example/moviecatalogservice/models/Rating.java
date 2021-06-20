package com.example.moviecatalogservice.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Rating {
	private String movieId;
	private int rating;

	public Rating(String movieId, int rating) {
		this.movieId = movieId;
		this.rating = rating;
	}
}
