package ru.chervenko.SensorRESTApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "measures")
public class Measures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    private float value;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "sensor_name")
    private String sensorName;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;


    public Measures() {
    }

    public Measures(float value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.sensorName = sensor.getName();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    @Override
    public String toString() {
        return "Measures{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
