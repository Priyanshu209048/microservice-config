package com.project.userservice.services.impl;

import com.project.userservice.entities.Hotel;
import com.project.userservice.entities.Rating;
import com.project.userservice.entities.User;
import com.project.userservice.exceptions.ResourceNotFoundException;
import com.project.userservice.external.services.HotelService;
import com.project.userservice.repo.UserRepo;
import com.project.userservice.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepo userRepo;
    private final RestTemplate restTemplate;
    private final HotelService hotelService;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, RestTemplate restTemplate, HotelService hotelService) {
        this.userRepo = userRepo;
        this.restTemplate = restTemplate;
        this.hotelService = hotelService;
    }

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID().toString());
        return this.userRepo.save(user);
    }

    @Override
    public User updateUser(User user, String id) {
        User updatedUser = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found !! id: " + id));
        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setAbout(user.getAbout());
        return this.userRepo.save(updatedUser);
    }

    @Override
    public User getUser(String id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found !! id: " + id));
        //Fetch ratings from the RATING SERVICE
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getId(), Rating[].class);
        logger.info("{}", (Object) ratingOfUser);

        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            /*ResponseEntity<Hotel> response = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = response.getBody();*/
            //logger.info("Response status code: {}", response.getStatusCode());

            ResponseEntity<Hotel> hotel = hotelService.getHotel(rating.getHotelId());
            logger.info("Status code: {}", hotel.getStatusCode());

            //set the hotel to string then return rating
            rating.setHotel(hotel.getBody());

            return rating;
        }).collect(Collectors.toList());

        user.setRatings(ratingList);
        return user;
    }

    @Override
    public List<User> getUsers() {
        /*List<User> users = this.userRepo.findAll();
        for (User user : users) {
            Rating[] ratingOfUser = restTemplate.getForObject("http://localhost:8083/ratings/users/" + user.getId(), Rating[].class);
            logger.info("Ratings for user {}: {}", user.getId(), ratingOfUser);
            List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
            user.setRatings(ratings);
        }
        return users;*/

        return this.userRepo.findAll()
                .stream()
                .peek(user -> {
                    Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getId(), Rating[].class);
                    logger.info("Ratings for user {}: {}", user.getId(), ratingOfUser);

                    List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

                    List<Rating> ratingList = ratings.stream().map(rating -> {

                        ResponseEntity<Hotel> hotel = hotelService.getHotel(rating.getHotelId());
                        logger.info("Status code : {}", hotel.getStatusCode());

                        rating.setHotel(hotel.getBody());

                        return rating;
                    }).collect(Collectors.toList());

                    user.setRatings(ratingList);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String id) {
        this.userRepo.deleteById(id);
    }
}
