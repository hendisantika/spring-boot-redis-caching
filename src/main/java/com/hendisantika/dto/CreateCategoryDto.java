package com.hendisantika.dto;

import com.hendisantika.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class CreateCategoryDto {
    @NotBlank(message = "This field is required")
    private String name;

    public Category toCategory() {
        Category category = new Category();

        category.setName(this.name);

        return category;
    }
}
