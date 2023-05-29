package ru.chervenko.SensorRESTApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chervenko.SensorRESTApp.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    Sensor findByName(String sensorName);

    String findSensorNameById(int id);

    Sensor findById(int id);
}
