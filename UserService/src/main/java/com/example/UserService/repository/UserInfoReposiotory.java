package com.example.UserService.repository;

import com.example.UserService.entities.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoReposiotory extends JpaRepository<UserInfo, String> {

    Optional<UserInfo> findByUserId(String userId);
}
