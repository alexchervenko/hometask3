package ru.chervenko.SensorRESTApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import ru.chervenko.SensorRESTApp.model.Sensor;
import ru.chervenko.SensorRESTApp.repositories.SensorRepository;

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
}
