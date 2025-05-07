package com.likelasttime.commerse.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reviews extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    Integer rating;

    String title;

    String content;

    Boolean verifiedPurchase;

    Integer helpfulVotes;
}
