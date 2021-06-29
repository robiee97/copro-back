package com.example.productLike.exceptions;

public class ProductAppException extends RuntimeException{
    public ProductAppException(String message, Exception e) { super(message, e); }
    public ProductAppException(String message) { super(message); }
}
