package com.example.UserService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgn
public class UserInfoDto
{
    Long id;

    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private Long phoneNumber;
}
