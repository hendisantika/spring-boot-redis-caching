package com.hendisantika.repository;

import com.hendisantika.model.CacheData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface CacheDataRepository extends CrudRepository<CacheData, String> {

    List<CacheData> findByIdContainingIgnoreCase(String keyword);
}
