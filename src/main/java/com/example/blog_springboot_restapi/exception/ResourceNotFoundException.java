package com.example.blog_springboot_restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
//        pass message to the super class constructor using super keyword
//        Post not found with id : 1
        super(String.format("%s not found with %s : '%s'", resourceName,
                fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


    //Define getter methods for this private fields
    public String getResourceName() {
        return resourceName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public String getFieldValue() {
        return fieldValue;
    }

}
