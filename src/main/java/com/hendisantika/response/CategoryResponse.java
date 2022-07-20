package com.hendisantika.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hendisantika.model.Category;
import lombok.Data;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
public class CategoryResponse {
    private Category data;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoryResponse(@JsonProperty("data") Category data) {
        this.data = data;
    }
}
