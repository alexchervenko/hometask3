package ru.chervenko.SensorRESTApp.util.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chervenko.SensorRESTApp.dto.MeasuresDTO;
import ru.chervenko.SensorRESTApp.services.SensorService;

@Component
public class MeasuresDTOValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasuresDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasuresDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasuresDTO measuresDTO = (MeasuresDTO) target;
        if (measuresDTO.getSensor() == null) {
            errors.rejectValue("sensor", "", "No sensor chosen in request");
        } else if (measuresDTO.getSensor().getName() == null || measuresDTO.getSensor().getName().isBlank()) {
            errors.rejectValue("sensor", "", "Name cant be null");
        } else if (sensorService.findByName(measuresDTO.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "No sensor with that name");
        }
    }
}
