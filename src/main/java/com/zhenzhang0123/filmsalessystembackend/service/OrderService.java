package com.zhenzhang0123.filmsalessystembackend.service;

import com.zhenzhang0123.filmsalessystembackend.dto.OrderListResponse;
import com.zhenzhang0123.filmsalessystembackend.dto.OrderRequest;
import com.zhenzhang0123.filmsalessystembackend.dto.OrderResponse;
import com.zhenzhang0123.filmsalessystembackend.model.Order;
import com.zhenzhang0123.filmsalessystembackend.model.Show;
import com.zhenzhang0123.filmsalessystembackend.repository.OrderRepository;
import com.zhenzhang0123.filmsalessystembackend.repository.ShowRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.OptimisticLockException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Transactional
    public OrderResponse createOrder(@Valid OrderRequest orderRequest) {
        Show show = showRepository.findById(orderRequest.getShowId())
                .orElseThrow(() -> new EntityNotFoundException("Show not found with ID: " + orderRequest.getShowId()));

        if (show.getRemainingTickets() < orderRequest.getTicketCount()) {
            throw new IllegalArgumentException("Not enough tickets available for the show.");
        }

        // Create the order
        Order order = new Order();
        order.setShow(show);
        order.setUserName(orderRequest.getUserName());
        order.setTicketCount(orderRequest.getTicketCount());

        // Update remaining tickets and sold tickets
        show.setRemainingTickets(show.getRemainingTickets() - orderRequest.getTicketCount());
        show.setSoldTickets(show.getSoldTickets() + orderRequest.getTicketCount());

        try {
            showRepository.save(show);
        } catch (OptimisticLockException e) {
            throw new IllegalStateException("Concurrent update detected. Please try again.");
        }

        // Save the order
        Order savedOrder = orderRepository.save(order);

        // Create the response
        OrderResponse response = new OrderResponse();
        response.setOrderId(savedOrder.getId());
        response.setShowId(savedOrder.getShow().getId());
        response.setTicketCount(savedOrder.getTicketCount());

        return response;
    }

    public Page<OrderListResponse> getOrdersByUserName(String userName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Order> ordersPage = orderRepository.findByUserName(userName, pageable);

        return ordersPage.map(order -> {
            Show show = order.getShow();
            double fullOrderPrice = show.getTicketPrice() * order.getTicketCount();

            return new OrderListResponse(
                    order.getId(),
                    show.getFilmName(),
                    show.getShowTime(),
                    order.getCreatedAt(),
                    order.getTicketCount(),
                    fullOrderPrice
            );
        });
    }
}
