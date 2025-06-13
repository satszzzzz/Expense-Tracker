package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
// No args constructor -> creates constructor automatically
// during compilation with no args i.e. UserRoles()
// Same for all args but this time constructor with
// all arguments/fields there
@Table(name = "roles")
public class UserRoles {

    // This generated value is for auto incrmentation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // attributes according to roles table
    @Column(name = "role_id")
    private Long role_id;
    private String name;

}
