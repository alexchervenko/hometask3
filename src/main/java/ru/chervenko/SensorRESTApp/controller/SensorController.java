package ru.chervenko.SensorRESTApp.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.chervenko.SensorRESTApp.dto.SensorDTO;
import ru.chervenko.SensorRESTApp.model.Sensor;
import ru.chervenko.SensorRESTApp.services.SensorService;
import ru.chervenko.SensorRESTApp.util.BindingResultMethod;
import ru.chervenko.SensorRESTApp.util.JsonErrorResponse;
import ru.chervenko.SensorRESTApp.util.sensorErrors.SensorNotCreatedException;
import ru.chervenko.SensorRESTApp.util.validators.SensorDTOValidator;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorDTOValidator sensorDTOValidator;

    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                            BindingResult bindingResult) {
        sensorDTOValidator.validate(sensorDTO, bindingResult);
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

    @ExceptionHandler
    private ResponseEntity<JsonErrorResponse> handleException(SensorNotCreatedException e) {
        JsonErrorResponse response = new JsonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
