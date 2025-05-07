package com.likelasttime.commerse.domain;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long productId;

    Double weight;

    @Column(columnDefinition = "json")
    String dimensions;

    String materials;

    String countryOfOrigin;

    String warrantyInfo;

    String cateInstructions;

    @Column(columnDefinition = "json")
    String additionalInfo;
}
