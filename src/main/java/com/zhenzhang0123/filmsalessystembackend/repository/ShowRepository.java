package com.zhenzhang0123.filmsalessystembackend.repository;

import com.zhenzhang0123.filmsalessystembackend.model.Show;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ShowRepository extends JpaRepository<Show, Long> {
    Page<Show> findByRemainingTicketsGreaterThanAndShowTimeAfter(int remainingTickets, LocalDateTime showTime, Pageable pageable);
}

