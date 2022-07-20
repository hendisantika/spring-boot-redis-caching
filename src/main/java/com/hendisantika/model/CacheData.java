package com.hendisantika.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:27
 * To change this template use File | Settings | File Templates.
 */
@AllArgsConstructor
@Getter
@Accessors(chain = true)
@RedisHash("cacheData")
public class CacheData {
    @Id
    private String key;

    @Indexed
    private String value;
}
