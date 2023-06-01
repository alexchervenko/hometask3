package ru.chervenko.SensorRESTApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chervenko.SensorRESTApp.model.Sensor;
import ru.chervenko.SensorRESTApp.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    public final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String sensorName) {
        return sensorRepository.findByName(sensorName);
    }

    public String findSensorNameById(int id) {
        return sensorRepository.findSensorNameById(id);
    }

    public Sensor findById(int id){
        return sensorRepository.findById(id);
    }
}
