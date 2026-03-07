package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.example.entities.UserInfo;
import org.springframework.stereotype.Service;

@Data
@Service
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoDto extends UserInfo
{
    private String firstName;

    private String lastName;

    private Long phoneNumber;

    private String email;

    private String profilePic;

    // This is only for code purposes, not for any database.
    // We want to get extra details, so extends UserInfo class
}
