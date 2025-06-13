package org.example.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.example.entities.UserInfo;
import org.springframework.stereotype.Service;

@Data
@Service
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class UserInfoDto extends UserInfo
{
    private String userName;

    private String lastName;

    private Long phoneNumber;

    private String email;

    // This is only for code purposes, not for any database.
    // We want to get extra details, so extends UserInfo class
}
