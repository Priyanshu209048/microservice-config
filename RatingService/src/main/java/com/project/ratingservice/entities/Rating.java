package com.project.ratingservice.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("user_rating")    //We can't use @Entity in mongodb
public class Rating {

    @Id     //Mongodb automatically generates the id
    private String ratingId;
    private String userId;
    private String hotelId;
    private int rating;
    private String feedback;

}
