package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entities.UserInfo;
//import org.example.eventProducer.UserInfoProducer;
import org.example.eventProducer.UserInfoProducer;
import org.example.model.UserInfoDto;
import org.example.model.UserInfoEvent;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

@Component
@Data
@AllArgsConstructor
public class UserDetailsServiceImplementation implements UserDetailsService
{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final UserInfoProducer userInfoProducer;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserInfo user = userRepository.findByUsername(username);

        if(user == null)
        {
            throw new RuntimeException("could not found user..!!");
        }
        return new CustomUserDetails(user);
    }

    public UserInfo checkIfAlreadyExists(UserInfoDto user)
    {
        return userRepository.findByUsername(user.getUsername());
    }

    public Boolean signupUser(UserInfoDto userInfoDto)
    {
        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if(Objects.nonNull(checkIfAlreadyExists(userInfoDto)))
            return false;
        String user_id = UUID.randomUUID().toString();
        userRepository.save(new UserInfo(user_id, userInfoDto.getUsername(), userInfoDto.getPassword(), new HashSet<>()));
        userInfoProducer.sentToTopic(transform(userInfoDto));
        return true;
    }

    private UserInfoEvent transform(UserInfoDto userInfoDto)
    {
        return UserInfoEvent.builder()
                .firstName(userInfoDto.getFirstName())
                .lastName(userInfoDto.getLastName())
                .email(userInfoDto.getEmail())
                .phoneNumber(userInfoDto.getPhoneNumber())
                .profilePic(userInfoDto.getProfilePic())
                .build();
    }
}
