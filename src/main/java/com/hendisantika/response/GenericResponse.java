package com.hendisantika.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
@Data
public class GenericResponse {
    private Map<String, Object> data;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GenericResponse(@JsonProperty("data") Map<String, Object> data) {
        this.data = data;
    }
}
