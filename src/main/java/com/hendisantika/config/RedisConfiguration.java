package com.hendisantika.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:36
 * To change this template use File | Settings | File Templates.
 */
@Configuration
@EnableRedisRepositories(value = "com.hendisantika.repository")
public class RedisConfiguration {
}
