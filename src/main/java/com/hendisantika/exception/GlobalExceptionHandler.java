package com.hendisantika.exception;

import com.hendisantika.response.InvalidDataResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InvalidDataResponse> methodArgumentNotValidException(
            MethodArgumentNotValidException ex, WebRequest request
    ) {
        HashMap<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(objectError -> {
            String field = "";

            // Custom validator parsing
            if (objectError.getArguments() != null && objectError.getArguments().length >= 2) {
                field = Objects.requireNonNull(objectError.getDefaultMessage()).split("\\|")[0];
            }

            if (field.length() > 0) {
                updateErrorHashMap(errors, field, objectError.getDefaultMessage().replace(field + "|", ""));
            }
        });

        ex.getBindingResult().getFieldErrors().forEach(fieldError ->
                updateErrorHashMap(errors, fieldError.getField(), fieldError.getDefaultMessage())
        );

        return new ResponseEntity<>(createInvalidDataResponse(errors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
