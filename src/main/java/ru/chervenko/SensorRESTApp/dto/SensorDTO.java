package ru.chervenko.SensorRESTApp.dto;

import jakarta.validation.constraints.Size;

public class SensorDTO {
    @Size(min = 3, max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
