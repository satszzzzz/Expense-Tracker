package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserInfo {
//    public String getUserId() {
//        return userId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public Set<UserRoles> getRoles() {
//        return roles;
//    }
//
//    public String getPassword() {
//        return password;
//    }

    @Id
    @Column(name = "user_id")
    private String userId;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", //UserRoles class one table
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRoles> roles = new HashSet<>();


    // all the roles of the user with "user_id" are stored in roles.
}
