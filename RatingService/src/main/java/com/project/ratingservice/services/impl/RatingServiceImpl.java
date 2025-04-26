package com.project.ratingservice.services.impl;

import com.project.ratingservice.entities.Rating;
import com.project.ratingservice.exceptions.ResourceNotFoundException;
import com.project.ratingservice.repo.RatingRepo;
import com.project.ratingservice.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepo ratingRepo;

    @Autowired
    public RatingServiceImpl(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @Override
    public Rating addRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRatings() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getAllRatingsByUser(String userId) {
        return ratingRepo.findRatingByUserId(userId);
    }

    @Override
    public List<Rating> getAllRatingsByHotel(String hotelId) {
        return ratingRepo.findRatingByHotelId(hotelId);
    }

    @Override
    public Rating getRatingById(String id) {
        return this.ratingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found in the server !! id : " + id));
    }

    @Override
    public void deleteRatingById(String id) {
        Rating rating = this.ratingRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found in the server !! id : " + id));
        this.ratingRepo.delete(rating);
    }

    @Override
    public Rating updateRating(Rating rating) {
        return null;
    }
}
