package ru.chervenko.SensorRESTApp.dto;

import ru.chervenko.SensorRESTApp.model.Sensor;
import ru.chervenko.SensorRESTApp.repositories.SensorRepository;

import java.util.Optional;

public class MeasuresDTO {
    private float value;
    private boolean raining;
    private Sensor sensor;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }


    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

}
