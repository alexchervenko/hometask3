package ru.chervenko.SensorRESTApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.chervenko.SensorRESTApp.model.Measures;
import ru.chervenko.SensorRESTApp.repositories.MeasuresRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasuresService {
    private final MeasuresRepository measuresRepository;

    @Autowired
    public MeasuresService(MeasuresRepository measuresRepository) {
        this.measuresRepository = measuresRepository;
    }

    public List<Measures> getMeasures(){
        return measuresRepository.findAll();
    }

    @Transactional
    public void save(Measures measures) {
        measures.setCreatedAt(new Date());
        measuresRepository.save(measures);
    }

    public int countAllByRainingTrue(){
        return measuresRepository.countAllByRainingTrue();
    }

}
