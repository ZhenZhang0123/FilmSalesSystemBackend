package com.zhenzhang0123.filmsalessystembackend.repository;

import com.zhenzhang0123.filmsalessystembackend.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserName(String userName, Pageable pageable);
}
