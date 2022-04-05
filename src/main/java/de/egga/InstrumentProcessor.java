package de.egga;

import java.io.PrintStream;

public class InstrumentProcessor {

    private final TaskDispatcher dispatcher;
    private final Instrument instrument;
    private final PrintStream out;

    public InstrumentProcessor(TaskDispatcher dispatcher, Instrument instrument, PrintStream out) {
        this.dispatcher = dispatcher;
        this.instrument = instrument;
        this.out = out;
    }

    public void process() {
        final String task = dispatcher.getTask();
        instrument.execute(
                task,
                () -> dispatcher.finishedTask(task),
                () -> out.println("Error occurred")
        );
    }
}
