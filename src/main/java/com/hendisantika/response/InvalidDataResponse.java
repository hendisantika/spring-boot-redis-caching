package com.hendisantika.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:32
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Accessors(chain = true)
public class InvalidDataResponse {
    private final Map<String, Map<String, List<String>>> data;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public InvalidDataResponse(@JsonProperty("data") Map<String, Map<String, List<String>>> data) {
        this.data = data;
    }
}
