package ru.chervenko.SensorRESTApp.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import ru.chervenko.SensorRESTApp.model.Sensor;


public class MeasuresDTO {

    @DecimalMin("-100")
    @DecimalMax("100")
    private float value;

    @NotNull
    private boolean raining;

    @NotNull
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
