package com.zhenzhang0123.filmsalessystembackend.service;

import com.zhenzhang0123.filmsalessystembackend.dto.OrderListResponse;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ShowRepository showRepository;

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

    public List<OrderListResponse> getOrdersByUserName(String userName) {
        List<Order> orders = orderRepository.findByUserName(userName);
        return orders.stream().map(order -> {
            Show show = showRepository.findById(order.getShowId())
                    .orElseThrow(() -> new RuntimeException("Show not found"));

            double fullOrderPrice = show.getTicketPrice() * order.getTicketCount();

            return new OrderListResponse(
                    order.getId(),
                    show.getFilmName(),
                    show.getShowTime(),
                    order.getCreatedAt(),
                    order.getTicketCount(),
                    fullOrderPrice
            );
        }).collect(Collectors.toList());
    }
}
