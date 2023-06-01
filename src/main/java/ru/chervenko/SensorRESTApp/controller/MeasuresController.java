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
import ru.chervenko.SensorRESTApp.util.JsonErrorResponse;
import ru.chervenko.SensorRESTApp.util.measuresErrors.MeasureNotCreatedException;
import ru.chervenko.SensorRESTApp.util.validators.MeasuresDTOValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measures")
public class MeasuresController {
    private final MeasuresService measuresService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final MeasuresDTOValidator measuresDTOValidator;

    @Autowired
    public MeasuresController(MeasuresService measuresService,
                              SensorService sensorService,
                              ModelMapper modelMapper,
                              MeasuresDTOValidator measuresDTOValidator) {
        this.measuresService = measuresService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.measuresDTOValidator = measuresDTOValidator;
    }

    @GetMapping()
    public List<MeasuresDTO> getMeasures(){
        return measuresService.getMeasures().stream()
                .map(this::convertToMeasuresDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int countRainyDays() {
        return measuresService.countAllByRainingTrue();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasuresDTO measuresDTO,
                                             BindingResult bindingResult) {
        measuresDTOValidator.validate(measuresDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            String errorMessage = BindingResultMethod.buildErrorsStringForBindindResult(bindingResult);
            throw new MeasureNotCreatedException(errorMessage);
        }
        measuresDTO.setSensor(sensorService.findByName(measuresDTO.getSensor().getName()).get());
        measuresService.save(convertToMeasures(measuresDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Measures convertToMeasures(MeasuresDTO measuresDTO) {
        return modelMapper.map(measuresDTO, Measures.class);
    }

    private MeasuresDTO convertToMeasuresDTO(Measures measures) {
        return modelMapper.map(measures, MeasuresDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<JsonErrorResponse> handleException(MeasureNotCreatedException e) {
        JsonErrorResponse response = new JsonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

}