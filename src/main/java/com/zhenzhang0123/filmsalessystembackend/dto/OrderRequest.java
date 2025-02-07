package com.zhenzhang0123.filmsalessystembackend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    @NotNull(message = "UserName is required")
    private String userName;

    @NotNull(message = "ShowId is required")
    private Long showId;

    @Min(value = 1, message = "Ticket count must be positive")
    private Integer ticketCount;
}
