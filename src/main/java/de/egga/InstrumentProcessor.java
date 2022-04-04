package de.egga;

public class InstrumentProcessor {

    private final TaskDispatcher dispatcher;
    private final Instrument instrument;

    public InstrumentProcessor(TaskDispatcher dispatcher, Instrument instrument) {
        this.dispatcher = dispatcher;
        this.instrument = instrument;
    }

    public void process() {
        // this method needs to be implemented
    }
}
