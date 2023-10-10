package com.example.demo;

public class Product {
    public String code;
    public String name;
    public String id_supplier;
    public String description;
    public String value ;

    public Product(String code, String name, String id_supplier, String description, String value) {
        this.code = code;
        this.name = name;
        this.id_supplier = id_supplier;
        this.description = description;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(String id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
