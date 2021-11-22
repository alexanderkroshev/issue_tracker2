package com.example.demo.issue;

public enum Status {
    RESOLVED("Resolved"),
    CREATED("Created"),
    IN_PROCESS("In process"),
    CLOSED("Closed");

    private final String name;

    Status(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
