package com.project.hotelservice.services.impl;

import com.project.hotelservice.entities.Hotel;
import com.project.hotelservice.exceptions.ResourceNotFoundException;
import com.project.hotelservice.repo.HotelRepo;
import com.project.hotelservice.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepo hotelRepo;

    @Autowired
    public HotelServiceImpl(HotelRepo hotelRepo) {
        this.hotelRepo = hotelRepo;
    }

    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepo.save(hotel);
    }

    @Override
    public Hotel updateHotel(Hotel hotel, String id) {
        Hotel update = this.hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found !! id : "+ id));
        update.setName(hotel.getName());
        update.setLocation(hotel.getLocation());
        update.setAbout(hotel.getAbout());
        return hotelRepo.save(update);
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found !! id : "+ id));
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelRepo.findAll();
    }

    @Override
    public void deleteHotel(String id) {
        Hotel update = hotelRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel not found !! id : "+ id));
        hotelRepo.delete(update);
    }
}
