package com.project.hotelservice.controllers;

import com.project.hotelservice.entities.Hotel;
import com.project.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity<?> createHotel(@RequestBody Hotel hotel) {
        Hotel create = hotelService.createHotel(hotel);
        return new ResponseEntity<>(create, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<?> getHotel(@PathVariable String hotelId) {
        Hotel hotel = hotelService.getHotel(hotelId);
        return new ResponseEntity<>(hotel, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllHotels() {
        return new ResponseEntity<>(hotelService.getHotels(), HttpStatus.OK);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<?> updateHotel(@PathVariable("hotelId") String hotelId, @RequestBody Hotel hotel) {
        Hotel update = this.hotelService.updateHotel(hotel, hotelId);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteHotel(@PathVariable String userId) {
        hotelService.deleteHotel(userId);
        return new ResponseEntity<>("Hotel Deleted Successfully !!", HttpStatus.OK);
    }

}
