package com.project.hotelservice.services;

import com.project.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);
    Hotel updateHotel(Hotel hotel, String id);
    Hotel getHotel(String id);
    List<Hotel> getHotels();
    void deleteHotel(String id);

}
