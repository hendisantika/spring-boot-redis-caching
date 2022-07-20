package com.hendisantika.controller;

import com.hendisantika.dto.CreateCategoryDto;
import com.hendisantika.model.Category;
import com.hendisantika.response.CategoryListResponse;
import com.hendisantika.response.CategoryResponse;
import com.hendisantika.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:46
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CreateCategoryDto createCategoryDto) {
        Category category = categoryService.create(createCategoryDto);

        return ResponseEntity.ok(new CategoryResponse(category));
    }

    @GetMapping
    public ResponseEntity<CategoryListResponse> getAll() {
        List<Category> categories = categoryService.findAll();

        return ResponseEntity.ok(new CategoryListResponse(categories));
    }
}
