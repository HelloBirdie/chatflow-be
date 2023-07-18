package com.hellobirdie.chatflow.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource is not found!");
    }

    public ResourceNotFoundException(String resource) {
        super(resource + " is not found!");
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " " + id + " not found!");
    }

    public ResourceNotFoundException(String resource, String type) {
        super(resource + " " + type + " not found!");
    }
}