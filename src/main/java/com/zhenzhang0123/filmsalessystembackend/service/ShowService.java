package com.zhenzhang0123.filmsalessystembackend.service;

import com.zhenzhang0123.filmsalessystembackend.dto.ShowResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.ShowSalesStatusResponse;
import com.zhenzhang0123.filmsalessystembackend.model.Show;
import com.zhenzhang0123.filmsalessystembackend.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowService {
    private final ShowRepository showRepository;

    public List<ShowResponse> getAvailableFutureShows() {
        List<Show> shows = showRepository.findByRemainingTicketsGreaterThanAndShowTimeAfter(0, LocalDateTime.now());
        return shows.stream()
                .map(show -> new ShowResponse(show.getId(), show.getFilmName(), show.getShowTime(), show.getTicketPrice(), show.getRemainingTickets()))
                .collect(Collectors.toList());
    }

    public List<ShowSalesStatusResponse> getShowSalesStatus() {
        List<Show> shows = showRepository.findAll();
        return shows.stream()
                .map(show -> new ShowSalesStatusResponse(
                        show.getId(),
                        show.getFilmName(),
                        show.getShowTime(),
                        show.getSoldTickets(),
                        show.getSoldTickets() * show.getTicketPrice()
                ))
                .collect(Collectors.toList());
    }
}
