package com.likelasttime.commerse.repository;

import com.likelasttime.commerse.domain.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Products, Long> {
}
