package com.example.UserService.service;

import com.example.UserService.entities.UserInfo;
import com.example.UserService.entities.UserInfoEvent;
import com.example.UserService.repository.UserInfoReposiotory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class UserService {

    private UserInfoReposiotory userInfoReposiotory;

    public UserInfoEvent createOrUpdateUser(UserInfoEvent userInfoEvent)
    {
        Function<UserInfo, UserInfo> updatingUser = user -> {
            user.setEmail(userInfoEvent.getEmail());
            user.setFirstName(userInfoEvent.getFirstName());
            user.setLastName(userInfoEvent.getLastName());
            user.setPhoneNumber(userInfoEvent.getPhoneNumber());
            user.setEmail(userInfoEvent.getEmail());
            return userInfoReposiotory.save(user);
        };

        Supplier<UserInfo> createUser = () -> {
            return userInfoReposiotory.save(userInfoEvent.transformUserInfoDto());
        };

        UserInfo userInfo = userInfoReposiotory.findByUserId(userInfoEvent.getUserId())
                .map(updatingUser)
                .orElseGet(createUser);

        return new UserInfoEvent(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );

    }

    public UserInfoEvent getUser(UserInfoEvent userInfoEvent) throws Exception
    {
        UserInfo userInfo = userInfoReposiotory.findByUserId(userInfoEvent.getUserId()).orElse(null);
        if(userInfo==null)
            throw new Exception("User not found");

        return new UserInfoEvent(
                userInfo.getUserId(),
                userInfo.getFirstName(),
                userInfo.getLastName(),
                userInfo.getPhoneNumber(),
                userInfo.getEmail(),
                userInfo.getProfilePic()
        );
    }
}
