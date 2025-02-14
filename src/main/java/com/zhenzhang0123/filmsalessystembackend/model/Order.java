package com.zhenzhang0123.filmsalessystembackend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @Column(nullable = false)
    private String userName;

    @Min(value = 1, message = "Ticket count must be positive")
    @Column(nullable = false)
    private int ticketCount;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
