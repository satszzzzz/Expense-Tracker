package com.ms.expenseService.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpenseDto {
    private String externalId;

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("merchant")
    private String merchant;

    @JsonProperty("created_at")
    private Timestamp createdAt;
}
