package ru.chervenko.SensorRESTApp.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chervenko.SensorRESTApp.dto.SensorDTO;
import ru.chervenko.SensorRESTApp.model.Sensor;
import ru.chervenko.SensorRESTApp.services.SensorService;
import ru.chervenko.SensorRESTApp.util.BindingResultMethod;
import ru.chervenko.SensorRESTApp.util.SensorNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    public final SensorService sensorService;
    private final ModelMapper modelMapper;

    public SensorController(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultMethod.buildErrorsStringForBindindResult(bindingResult);
            throw new SensorNotCreatedException(errorMessage);
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO personDTO) {
        return modelMapper.map(personDTO, Sensor.class);
    }
}
