package com.zhenzhang0123.filmsalessystembackend.service;

import com.zhenzhang0123.filmsalessystembackend.dto.OrderRequest;
import com.zhenzhang0123.filmsalessystembackend.dto.OrderResponse;
import com.zhenzhang0123.filmsalessystembackend.model.Order;
import com.zhenzhang0123.filmsalessystembackend.model.Show;
import com.zhenzhang0123.filmsalessystembackend.repository.OrderRepository;
import com.zhenzhang0123.filmsalessystembackend.repository.ShowRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShowRepository showRepository;
    private final AuthenticationManager authenticationManager;

    public OrderResponse createOrder(@Valid OrderRequest orderRequest) {
        Optional<Show> showOpt = showRepository.findById(orderRequest.getShowId());
        if (showOpt.isEmpty()) {
            throw new EntityNotFoundException("Show not found with ID: " + orderRequest.getShowId());
        }

        Show show = showOpt.get();
        if (show.getRemainingTickets() < orderRequest.getTicketCount()) {
            throw new IllegalArgumentException("Not enough tickets available for the show.");
        }

        // Create the order
        Order order = new Order();
        order.setShowId(orderRequest.getShowId());
        order.setUserName(orderRequest.getUserName());
        order.setTicketCount(orderRequest.getTicketCount());

        // Update remaining tickets and sold tickets
        show.setRemainingTickets(show.getRemainingTickets() - orderRequest.getTicketCount());
        show.setSoldTickets(show.getSoldTickets() + orderRequest.getTicketCount());
        showRepository.save(show);

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Create the response
        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setShowId(savedOrder.getShowId());
        response.setTicketCount(savedOrder.getTicketCount());

        return response;
    }
}
