package com.likelasttime.commerse.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Products extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String slug;

    String shortDescription;

    String fullDescription;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    Sellers sellers;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brands brands;

    String status;
}
