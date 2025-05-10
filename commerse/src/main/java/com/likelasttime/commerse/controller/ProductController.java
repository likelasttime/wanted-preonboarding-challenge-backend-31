package com.likelasttime.commerse.controller;

import com.likelasttime.commerse.dto.request.CreateProductRequest;
import com.likelasttime.commerse.dto.response.SuccessResponse;
import com.likelasttime.commerse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/api/products")
    public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest) {
        return (ResponseEntity
                .status(CREATED)
                .body(SuccessResponse.builder()
                        .success(true)
                .data(productService.createProduct(createProductRequest))
                        .message("상품이 성공적으로 등록되었습니다.")
                .build())
        );
    }
}
