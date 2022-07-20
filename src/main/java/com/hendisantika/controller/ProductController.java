package com.hendisantika.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.repository.CacheDataRepository;
import com.hendisantika.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
