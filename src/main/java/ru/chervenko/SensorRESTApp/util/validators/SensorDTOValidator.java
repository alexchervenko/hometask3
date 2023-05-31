package ru.chervenko.SensorRESTApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.chervenko.SensorRESTApp.dto.SensorDTO;
import ru.chervenko.SensorRESTApp.services.SensorService;

@Component
public class SensorDTOValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        if(sensorService.findByName(sensorDTO.getName()).isPresent()){
            errors.rejectValue("name", "", "This name is already exist");
        }

    }
}
