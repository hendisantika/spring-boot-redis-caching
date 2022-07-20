package com.hendisantika.config;

import com.hendisantika.service.CategoryService;
import com.hendisantika.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:44
 * To change this template use File | Settings | File Templates.
 */
@Component
@RequiredArgsConstructor
public class DataSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private static final String CATEGORY_PHONE = "Phone";
    private static final String CATEGORY_COMPUTER = "Computer";
    private static final String CATEGORY_TV = "TV";
    private static final String CATEGORY_GAME = "Game";
    private static final String CATEGORY_TABLET = "Tablet";

    private final CategoryService categoryService;

    private final ProductService productService;
}