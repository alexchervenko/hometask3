package ru.chervenko.SensorRESTApp.util.measuresErrors;

public class MeasureNotCreatedException extends RuntimeException{
    public MeasureNotCreatedException (String msg) {
        super(msg);
    }
}
