package com.example.UserService.controller;

import com.example.UserService.entities.UserInfoEvent;
import com.example.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    @PostMapping("/user/v1/createOrUpdate")
    public ResponseEntity createOrUpdate(@RequestBody UserInfoEvent userInfoEvent)
    {
        try {
            UserInfoEvent userInfoEvent_new = userService.createOrUpdateUser(userInfoEvent);
            return new ResponseEntity<>(userInfoEvent_new, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>("User couldn't be created / updated : "+e, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/v1/getUser")
    public ResponseEntity getUser(@RequestBody UserInfoEvent userInfoEvent)
    {
        try
        {
            UserInfoEvent userInfoEvent_response = userService.getUser(userInfoEvent);
            return new ResponseEntity<>(userInfoEvent_response, HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("User couldn't be found : "+e, HttpStatus.NOT_FOUND);
        }

    }
}
