package com.zhenzhang0123.filmsalessystembackend.service;

import com.zhenzhang0123.filmsalessystembackend.dto.ShowResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.ShowSalesStatusResponse;
import com.zhenzhang0123.filmsalessystembackend.model.Show;
import com.zhenzhang0123.filmsalessystembackend.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    public Page<ShowResponse> getAvailableFutureShows(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("showTime").ascending());
        Page<Show> shows = showRepository.findByRemainingTicketsGreaterThanAndShowTimeAfter(0, LocalDateTime.now(), pageable);

        return shows.map(show -> new ShowResponse(
                show.getId(),
                show.getFilmName(),
                show.getShowTime(),
                show.getTicketPrice(),
                show.getRemainingTickets()
        ));
    }

    public Page<ShowSalesStatusResponse> getShowSalesStatus(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("showTime").descending());
        Page<Show> shows = showRepository.findAll(pageable);

        return shows.map(show -> new ShowSalesStatusResponse(
                show.getId(),
                show.getFilmName(),
                show.getShowTime(),
                show.getSoldTickets(),
                show.getSoldTickets() * show.getTicketPrice()
        ));
    }
}
