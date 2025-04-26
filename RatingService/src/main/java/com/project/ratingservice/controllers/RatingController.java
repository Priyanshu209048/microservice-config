package com.project.ratingservice.controllers;

import com.project.ratingservice.entities.Rating;
import com.project.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity<?> addRating(@RequestBody Rating rating) {
        Rating addRating = ratingService.addRating(rating);
        return new ResponseEntity<>(addRating, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllRatings() {
        return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getAllRatingsByUser(@PathVariable String userId) {
        return new ResponseEntity<>(ratingService.getAllRatingsByUser(userId), HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<?> getAllRatingsByHotel(@PathVariable String hotelId) {
        return new ResponseEntity<>(ratingService.getAllRatingsByHotel(hotelId), HttpStatus.OK);
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<?> getRatingById(@PathVariable String ratingId) {
        return new ResponseEntity<>(ratingService.getRatingById(ratingId), HttpStatus.OK);
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<?> deleteRating(@PathVariable String ratingId) {
        ratingService.deleteRatingById(ratingId);
        return new ResponseEntity<>("Rating deleted Successfully !!", HttpStatus.OK);
    }

}
