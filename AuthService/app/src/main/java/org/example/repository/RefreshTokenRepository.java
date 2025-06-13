package org.example.repository;

import org.example.entities.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer>
{
    // when we interact with DB, SB calls it repositories
    Optional<RefreshToken> findByToken(String token);
    // find everything from Refresh Token (DB) class in entities where token is equal to the token input we have provided and return that row as a RfreshToken object
}
