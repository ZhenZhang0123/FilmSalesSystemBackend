package com.zhenzhang0123.filmsalessystembackend.controller;

import com.zhenzhang0123.filmsalessystembackend.dto.ShowResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.ShowSalesStatusResponse;
import com.zhenzhang0123.filmsalessystembackend.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping("/user/availableFutureShows")
    public ResponseEntity<List<ShowResponse>> getAvailableFutureShows() {
        List<ShowResponse> shows = showService.getAvailableFutureShows();
        return ResponseEntity.ok(shows);
    }

    @GetMapping("/admin/showSalesStatus")
    public ResponseEntity<List<ShowSalesStatusResponse>> getShowSalesStatus() {
        List<ShowSalesStatusResponse> salesStatus = showService.getShowSalesStatus();
        return ResponseEntity.ok(salesStatus);
    }
}

