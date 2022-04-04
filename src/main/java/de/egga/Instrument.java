package de.egga;

public interface Instrument {

    // starts the instrument, which will begin to execute the task passed to it
    // the method will return immediately
    // (we assume that the instrument implementation is asynchronous)
    // will throw an IllegalArgumentException if the task passed in is null
    void execute(String task,

    // is fired when the instrument finishes executing
                 Runnable eventHandlerFinished,

    // when the instrument detects an error situation during executing
    // (note that the Execute method will already have returned the control
    // flow to the caller due to its asynchronous implementation)
                 Runnable eventHandlerError);

}
