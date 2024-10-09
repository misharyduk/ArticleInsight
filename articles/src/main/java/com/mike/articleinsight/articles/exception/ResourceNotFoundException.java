package com.mike.articleinsight.articles.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
       public ResourceNotFoundException(String resourceName, String fieldName, String fiendValue) {
        super(String.format("%s has not been found with %s equals %s", resourceName, fieldName, fiendValue));
    }
}
