package com.project.userservice;

import com.project.userservice.entities.Rating;
import com.project.userservice.external.services.RatingService;
import com.project.userservice.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RatingService ratingService;

    @Test
    void contextLoads() {
    }

    /*@Test
    void createRating() {
        Rating rating = Rating.builder()
                .userId("426a1b88-c0f9-475a-93b2-83fc493bd5ba")
                .hotelId("7b07605d-020a-4949-b804-682bc1ee8c88")
                .rating(6)
                .feedback("This is for tasting which is created by feign client")
                .build();
        Rating savedRating = ratingService.saveRating(rating);
        logger.info("Saved Rating: {}", savedRating);
    }*/

}
