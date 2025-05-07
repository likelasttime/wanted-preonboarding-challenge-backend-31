package com.likelasttime.commerse.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductOptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "option_group_id")
    ProductOptionGroups productOptionGroups;

    String name;

    Double additionalPrice;

    String sku;

    Integer stock;

    Integer displayOrder;
}
