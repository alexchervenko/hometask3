package ru.chervenko.SensorRESTApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.chervenko.SensorRESTApp.model.Measures;

public interface MeasuresRepository extends JpaRepository <Measures, Integer>{
}
