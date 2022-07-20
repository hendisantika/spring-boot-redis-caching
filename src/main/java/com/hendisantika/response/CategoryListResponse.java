package com.hendisantika.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hendisantika.model.Category;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
@Setter
@Getter
@Accessors(chain = true)
public class CategoryListResponse {
    private List<Category> data;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoryListResponse(@JsonProperty("data") List<Category> data) {
        this.data = data;
    }
}
