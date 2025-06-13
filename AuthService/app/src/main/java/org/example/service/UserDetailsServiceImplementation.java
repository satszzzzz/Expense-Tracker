package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.entities.UserInfo;
import org.example.model.UserInfoDto;
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
        userRepository.save(new UserInfo(UUID.randomUUID().toString(), userInfoDto.getUsername(), userInfoDto.getPassword(), new HashSet<>()));
        return true;
    }
}
