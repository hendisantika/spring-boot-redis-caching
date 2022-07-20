package com.hendisantika.exception;

import com.hendisantika.response.InvalidDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-redis-caching
 * User: powercommerce
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 7/20/22
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    private HashMap<String, Object> formatMessage(String message) {
        HashMap<String, Object> result = new HashMap<>();
        result.put("message", message);

        return result;
    }

    public void updateErrorHashMap(Map<String, List<String>> errors, String field, String message) {
        if (errors.containsKey(field)) {
            List<String> strings = errors.get(field);
            strings.add(message);

            errors.put(field, strings);
        } else {
            List<String> strings = new ArrayList<>();
            strings.add(message);

            errors.put(field, strings);
        }

    }

    private InvalidDataResponse createInvalidDataResponse(HashMap<String, List<String>> errors) {
        HashMap<String, Map<String, List<String>>> result = new HashMap<>();
        result.put("errors", errors);

        return new InvalidDataResponse(result);
    }
}
