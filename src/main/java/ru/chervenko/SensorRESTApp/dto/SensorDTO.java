package ru.chervenko.SensorRESTApp.dto;

import jakarta.persistence.Column;

public class SensorDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
