package ru.chervenko.SensorRESTApp.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chervenko.SensorRESTApp.dto.MeasuresDTO;
import ru.chervenko.SensorRESTApp.model.Measures;
import ru.chervenko.SensorRESTApp.services.MeasuresService;
import ru.chervenko.SensorRESTApp.services.SensorService;
import ru.chervenko.SensorRESTApp.util.BindingResultMethod;
import ru.chervenko.SensorRESTApp.util.SensorNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/measures")
public class MeasuresController {
    private final MeasuresService measuresService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasuresController(MeasuresService measuresService, SensorService sensorService, ModelMapper modelMapper) {
        this.measuresService = measuresService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<Measures> getMeasures(){
        return measuresService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasuresDTO measuresDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultMethod.buildErrorsStringForBindindResult(bindingResult);
            throw new SensorNotCreatedException(errorMessage);
        }
        if(sensorService.findByName(measuresDTO.getSensor().getName()) != null){
            measuresDTO.setSensor(sensorService.findByName(measuresDTO.getSensor().getName()));
            measuresService.save(convertToMeasures(measuresDTO));
            return ResponseEntity.ok(HttpStatus.OK);
        } else {
            return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
        }
    }

    private Measures convertToMeasures(MeasuresDTO measuresDTO) {
        return modelMapper.map(measuresDTO, Measures.class);
    }

}