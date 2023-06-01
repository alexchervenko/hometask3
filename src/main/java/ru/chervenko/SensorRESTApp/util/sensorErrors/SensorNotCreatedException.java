package ru.chervenko.SensorRESTApp.util.sensorErrors;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String msg){
        super(msg);
    }

}
