package com.springSecurityP1.SpringSecurity.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String refreshToken;
    @CreationTimestamp
    private LocalDateTime lastCreatedAt;
    @ManyToOne
    private User user;
}
