package de.egga;

public interface TaskDispatcher {

    // returns the next task to execute on the instrument
    String getTask();

    void finishedTask(String task);

}
