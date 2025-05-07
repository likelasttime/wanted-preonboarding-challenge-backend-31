package com.likelasttime.commerse.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Products products;

    String url;

    String altText;

    Boolean isPrimary;

    Integer displayOrder;

    @ManyToOne
    @JoinColumn(name = "option_id")
    ProductOptions productOptions;
}
