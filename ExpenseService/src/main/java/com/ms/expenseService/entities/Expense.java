package com.ms.expenseService.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="Expense")
public class Expense {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "external_id")
    private String externalId;

    @Column(name="user_id")
    private String userId;

    @Column(name="amount")
    private String amount;

    @Column(name="currency")
    private String currency;

    @Column(name="merchant")
    private String merchant;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @PrePersist
    @PreUpdate
    private void generateExternalId()
    {
        this.externalId = UUID.randomUUID().toString();
    }
}
