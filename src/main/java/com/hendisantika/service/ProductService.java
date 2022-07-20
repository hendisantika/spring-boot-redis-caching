package com.hendisantika.service;

import com.hendisantika.dto.CreateProductDto;
import com.hendisantika.dto.SearchProductDto;
import com.hendisantika.model.Category;
import com.hendisantika.model.Product;
import com.hendisantika.repository.CategoryRepository;
import com.hendisantika.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }


    public List<Product> search(SearchProductDto searchProductDto) throws InterruptedException {
        // Simulate server pressure and high computation
        Thread.sleep(3000);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteriaQuery.from(Product.class);

        Metamodel metamodel = entityManager.getMetamodel();
        EntityType<Product> productMetaModel = metamodel.entity(Product.class);

        Join<Product, Category> joinCategory = product.join(productMetaModel.getSingularAttribute("category", Category.class));

        List<Predicate> predicateList = new ArrayList<>();

        if (searchProductDto.getName() != null) {
            predicateList.add(
                    criteriaBuilder.like(product.get("name"), "%" + searchProductDto.getName().trim() + "%")
            );
        }

        if (searchProductDto.getCategory() != null) {
            predicateList.add(
                    criteriaBuilder.equal(joinCategory.get("name"), searchProductDto.getCategory())
            );
        }

        if (searchProductDto.getMinPrice() > 0 && searchProductDto.getMaxPrice() > 0) {
            predicateList.add(
                    criteriaBuilder.between(product.get("price"), searchProductDto.getMinPrice(), searchProductDto.getMaxPrice())
            );
        } else {
            if (searchProductDto.getMinPrice() > 0) {
                predicateList.add(
                        criteriaBuilder.gt(product.get("price"), searchProductDto.getMinPrice())
                );
            }

            if (searchProductDto.getMaxPrice() > 0) {
                predicateList.add(
                        criteriaBuilder.lt(product.get("price"), searchProductDto.getMaxPrice())
                );
            }
        }

        if (searchProductDto.getAvailable() != null) {
            predicateList.add(criteriaBuilder.equal(product.get("isAvailable"), Objects.equals(searchProductDto.getAvailable(), "yes") ? 1 : 0));
        }

        Predicate[] predicates = new Predicate[predicateList.size()];

        predicateList.toArray(predicates);

        if (!predicateList.isEmpty()) {
            criteriaQuery.where(predicates);
        }

        criteriaQuery.select(product).orderBy(criteriaBuilder.desc(product.get("createdAt")));
        // criteriaQuery.orderBy(criteriaBuilder.desc(product.get("createdAt")), criteriaBuilder.asc(product.get("price")));

        TypedQuery<Product> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }
}
