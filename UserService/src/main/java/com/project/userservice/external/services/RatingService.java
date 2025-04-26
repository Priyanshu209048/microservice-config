package com.project.userservice.external.services;

import com.project.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @GetMapping("/ratings/{ratingId}")
    Rating getRating(@PathVariable String ratingId);

    @PostMapping("/ratings")
    Rating saveRating(Rating rating);

    @GetMapping("/ratings")
    List<Rating> getRatings();

    @GetMapping("/ratings/user/{userId}")
    List<Rating> getRatingsByUserId(@PathVariable String userId);

    @GetMapping("/ratings/hotel/{hotelId}")
    List<Rating> getRatingsByHotelId(@PathVariable String hotelId);

    @DeleteMapping("/ratings/{ratingId}")
    void deleteRating(@PathVariable String ratingId);

}
