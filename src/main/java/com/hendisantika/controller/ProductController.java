package com.hendisantika.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.dto.CreateProductDto;
import com.hendisantika.dto.SearchProductDto;
import com.hendisantika.model.CacheData;
import com.hendisantika.model.Product;
import com.hendisantika.repository.CacheDataRepository;
import com.hendisantika.response.ProductListResponse;
import com.hendisantika.response.ProductResponse;
import com.hendisantika.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<ProductListResponse> getAll() throws InterruptedException, JsonProcessingException {
        Optional<CacheData> optionalCacheData = cacheDataRepository.findById("allProducts");

        // Cache hit
        if (optionalCacheData.isPresent()) {
            String productAsString = optionalCacheData.get().getValue();

            TypeReference<List<Product>> mapType = new TypeReference<List<Product>>() {
            };
            List<Product> productList = objectMapper.readValue(productAsString, mapType);

            return ResponseEntity.ok(new ProductListResponse(productList));
        }

        // Cache miss
        List<Product> productList = productService.findAll();
        String productsAsJsonString = objectMapper.writeValueAsString(productList);
        CacheData cacheData = new CacheData("allProducts", productsAsJsonString);

        cacheDataRepository.save(cacheData);

        return ResponseEntity.ok(new ProductListResponse(productList));
    }

    @GetMapping("/search")
    public ResponseEntity<ProductListResponse> search(@Valid SearchProductDto searchProductDto) throws InterruptedException, JsonProcessingException {
        String cacheKey = searchProductDto.buildCacheKey("searchProducts");

        Optional<CacheData> optionalCacheData = cacheDataRepository.findById(cacheKey);

        // Cache hit
        if (optionalCacheData.isPresent()) {
            String productAsString = optionalCacheData.get().getValue();

            TypeReference<List<Product>> mapType = new TypeReference<List<Product>>() {
            };
            List<Product> productList = objectMapper.readValue(productAsString, mapType);

            return ResponseEntity.ok(new ProductListResponse(productList));
        }

        List<Product> productList = productService.search(searchProductDto);

        String productsAsJsonString = objectMapper.writeValueAsString(productList);
        CacheData cacheData = new CacheData(cacheKey, productsAsJsonString);

        cacheDataRepository.save(cacheData);

        cacheDataRepository.deleteById("allProducts");

        return ResponseEntity.ok(new ProductListResponse(productList));
    }
}
