# Instrument Processor

Java version for the Instrument Processor Kata by Urs Enzler:
https://www.planetgeek.ch/2010/03/28/mocking-kata-instrument-processor-net/

## Your Task
The exercise is to implement the InstrumentProcessor in a way that:
* When the method process() is called then the InstrumentProcessor gets the
  next task from the  task dispatcher and executes it on the instrument.
* When the Execute method of the instrument throws an exception then this
  exception is passed  on to the caller of the process() method.
* When the instrument fires the finished event then the InstrumentProcessor
  calls the task  dispatcher’s FinishedTask method with the correct task.
* When the instrument fires the Error event then the InstrumentProcessor
  writes the string  “Error occurred” to the console.
