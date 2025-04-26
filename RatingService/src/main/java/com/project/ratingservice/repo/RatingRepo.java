package com.project.ratingservice.repo;

import com.project.ratingservice.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepo extends MongoRepository<Rating, String> {

    List<Rating> findRatingByUserId(String userId);
    List<Rating> findRatingByHotelId(String hotelId);

}
