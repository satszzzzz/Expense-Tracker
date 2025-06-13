package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.entities.RefreshToken;
import org.example.model.UserInfoDto;
import org.example.response.JwtResponseDTO;
import org.example.service.JwtService;
import org.example.service.RefereshTokenService;
import org.example.service.UserDetailsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController
{
    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefereshTokenService refreshTokenService;

    @Autowired
    private UserDetailsServiceImplementation userDetailsServiceImpl;

    @PostMapping("auth/v1/signup")
    public ResponseEntity SignUp(@RequestBody UserInfoDto userInfoDto)
    {
        try
        {
            Boolean isSignedUp = userDetailsServiceImpl.signupUser(userInfoDto);
            if(Boolean.FALSE.equals(isSignedUp))
            {
                return new ResponseEntity<>("Already exist", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.creteRefreshToken(userInfoDto.getUsername());
            System.out.println(1);
            String jwtToken = jwtService.GenerateToken(userInfoDto.getUsername());
            return new ResponseEntity<>(JwtResponseDTO.builder().accessToken(jwtToken).token(refreshToken.getToken()).build(), HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>("Exception in user service: "+e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
