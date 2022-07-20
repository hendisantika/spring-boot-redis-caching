package com.hendisantika.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.dto.CreateProductDto;
import com.hendisantika.model.Product;
import com.hendisantika.repository.CacheDataRepository;
import com.hendisantika.response.ProductResponse;
import com.hendisantika.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:47
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final CacheDataRepository cacheDataRepository;

    private final ObjectMapper objectMapper;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody CreateProductDto createProductDto) {
        Product product = productService.create(createProductDto);

        return ResponseEntity.ok(new ProductResponse(product));
    }
}
