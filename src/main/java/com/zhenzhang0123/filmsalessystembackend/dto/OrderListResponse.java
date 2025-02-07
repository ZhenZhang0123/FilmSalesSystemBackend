package com.zhenzhang0123.filmsalessystembackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderListResponse {
    private Long orderId;
    private String filmName;
    private LocalDateTime showTime;
    private LocalDateTime boughtTicketTime;
    private int boughtTicketCount;
    private double fullOrderPrice;
}

