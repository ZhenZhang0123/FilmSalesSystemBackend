package com.zhenzhang0123.filmsalessystembackend.controller;

import com.zhenzhang0123.filmsalessystembackend.dto.ShowResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.ShowSalesStatusResponse;
import com.zhenzhang0123.filmsalessystembackend.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/user/availableFutureShows")
    public ResponseEntity<Page<ShowResponse>> getAvailableFutureShows(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ShowResponse> shows = showService.getAvailableFutureShows(page, size);
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/admin/showSalesStatus")
    public ResponseEntity<Page<ShowSalesStatusResponse>> getShowSalesStatus(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<ShowSalesStatusResponse> salesStatus = showService.getShowSalesStatus(page, size);
        return ResponseEntity.ok(salesStatus);
    }
}


