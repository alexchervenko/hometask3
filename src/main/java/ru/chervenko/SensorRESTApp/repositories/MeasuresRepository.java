package ru.chervenko.SensorRESTApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chervenko.SensorRESTApp.model.Measures;

import java.util.List;

@Repository
public interface MeasuresRepository extends JpaRepository <Measures, Integer>{
}
