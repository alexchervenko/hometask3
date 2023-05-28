package ru.chervenko.SensorRESTApp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "measures")
public class Measures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    @Column(name = "sensor_id")
    private int sensorId;

    @Column(name = "sensor_name")
    private String sensorName;

    @Column(name = "value")
    private float value;

    @Column(name = "raining")
    private boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    public Measures() {
    }

    public Measures(String sensorName, float value, boolean raining, Sensor sensor) {
        this.sensorName = sensorName;
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
//
//    public int getSensorId() {
//        return sensorId;
//    }
//
//    public void setSensorId(int sensorId) {
//        this.sensorId = sensorId;
//    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
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

    @Override
    public String toString() {
        return "Measures{" +
                "id=" + id +
                ", sensorId=" + sensorId +
                ", sensorName='" + sensorName + '\'' +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                '}';
    }
}
