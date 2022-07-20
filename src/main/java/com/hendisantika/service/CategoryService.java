package com.hendisantika.service;

import com.hendisantika.dto.CreateCategoryDto;
import com.hendisantika.model.Category;
import com.hendisantika.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category create(CreateCategoryDto createCategoryDto) {
        return categoryRepository.save(createCategoryDto.toCategory());
    }

    public List<Category> findAll() {
        return categoryRepository.findAllByIdGreaterThanOrderByIdDesc(0);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
