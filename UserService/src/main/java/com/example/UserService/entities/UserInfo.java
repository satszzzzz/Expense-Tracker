package com.example.UserService.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users_info")
public class UserInfo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    Long id;

//    @JsonProperty("user_id")
    @Id
    private String userId;

//    @JsonProperty("first_name")
    private String firstName;

//    @JsonProperty("last_name")
    private String lastName;

//    @JsonProperty("phone_number")
    private Long phoneNumber;

//    @JsonProperty("email")
    private String email;

//    @JsonProperty("profile_pic")
    private String profilePic;
}
