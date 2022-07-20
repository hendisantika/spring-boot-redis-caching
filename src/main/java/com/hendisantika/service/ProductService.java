package com.hendisantika.service;

import com.hendisantika.dto.CreateProductDto;
import com.hendisantika.model.Category;
import com.hendisantika.model.Product;
import com.hendisantika.repository.CategoryRepository;
import com.hendisantika.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public Product create(CreateProductDto createProductDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(createProductDto.getCategoryId());

        if (optionalCategory.isEmpty()) {
            throw new RuntimeException("The category not found");
        }

        Product product = createProductDto.toProduct();
        product.setCategory(optionalCategory.get());

        return productRepository.save(product);
    }

    public List<Product> findAll() throws InterruptedException {
        // Simulate server pressure and high computation
        Thread.sleep(3000);

        return productRepository.findAllByIdGreaterThanOrderByIdDesc(0);
    }
}
