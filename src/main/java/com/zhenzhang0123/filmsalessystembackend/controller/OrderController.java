package com.zhenzhang0123.filmsalessystembackend.controller;

import com.zhenzhang0123.filmsalessystembackend.dto.OrderListResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.OrderRequest;
import com.zhenzhang0123.filmsalessystembackend.dto.OrderResponse;
import com.zhenzhang0123.filmsalessystembackend.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orderTicket")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        OrderResponse createdOrder = orderService.createOrder(orderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @GetMapping("/myOrders")
    public ResponseEntity<List<OrderListResponse>> getMyOrders(@RequestParam String userName) {
        List<OrderListResponse> orders = orderService.getOrdersByUserName(userName);
        return ResponseEntity.ok(orders);
    }
}
