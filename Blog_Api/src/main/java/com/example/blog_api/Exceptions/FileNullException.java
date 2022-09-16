package com.example.blog_api.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileNullException extends RuntimeException{
    private int code;
    private String message;
    private String field;
    private String objectName;
}