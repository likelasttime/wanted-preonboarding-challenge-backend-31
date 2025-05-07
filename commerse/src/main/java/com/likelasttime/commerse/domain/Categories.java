package com.likelasttime.commerse.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String slug;

    String description;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    Categories categories;

    int level;

    String imageUrl;
}
