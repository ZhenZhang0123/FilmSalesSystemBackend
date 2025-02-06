package com.zhenzhang0123.filmsalessystembackend.repository;

import com.zhenzhang0123.filmsalessystembackend.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    List<Show> findByRemainingTicketsGreaterThanAndShowTimeAfter(int remainingTickets, LocalDateTime now);
}
