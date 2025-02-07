package com.zhenzhang0123.filmsalessystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowSalesStatusResponse {
    private Long showId;
    private String filmName;
    private LocalDateTime showTime;
    private int soldTickets;
    private double totalRevenue;
}
