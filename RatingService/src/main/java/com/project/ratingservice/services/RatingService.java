package com.project.ratingservice.services;

import com.project.ratingservice.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating addRating(Rating rating);
    List<Rating> getRatings();
    List<Rating> getAllRatingsByUser(String userId);
    List<Rating> getAllRatingsByHotel(String hotelId);
    Rating getRatingById(String id);
    void deleteRatingById(String id);
    Rating updateRating(Rating rating);

}
