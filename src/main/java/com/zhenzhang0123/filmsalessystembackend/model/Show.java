package com.zhenzhang0123.filmsalessystembackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

@Entity
@Data
public class Show {
    @Id
    private int id;
    private String filmName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime showTime;
    private double ticketPrice;
    private int remainingTickets;
    private int soldTickets;
    private ReentrantLock lock = new ReentrantLock();

    public boolean updateTickets(int count) {
        lock.lock();
        try {
            if (remainingTickets >= count) {
                remainingTickets -= count;
                soldTickets += count;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public double getTotalAmount() {
        return ticketPrice * soldTickets;
    }
}

