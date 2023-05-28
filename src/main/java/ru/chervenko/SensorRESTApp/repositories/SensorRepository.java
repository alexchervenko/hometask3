package ru.chervenko.SensorRESTApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chervenko.SensorRESTApp.model.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer> {
}
