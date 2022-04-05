package de.egga;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstrumentProcessorTest {

    @Mock
    Instrument instrument;

    @Mock
    TaskDispatcher dispatcher;

    @Mock
    PrintStream out;

    @InjectMocks
    InstrumentProcessor processor;

    @Test
    void givenATaskWhenProcessIsCalledThenTheProcessorGetsTheNextTaskFromTheDispatcherAndExecutesItOnTheInstrument() {
        final String task = "myTask";
        when(dispatcher.getTask()).thenReturn(task);

        processor.process();

        verify(instrument).execute(eq(task), any(), any());
    }

    @Test
    void WhenTheExecuteMethodOfTheInstrumentThrowsAnExceptionThenThisExceptionIsPassedOnToTheCallerOfTheProcessMethod() {
        final RuntimeException runtimeException = new RuntimeException("this failed!");

        doThrow(runtimeException).when(instrument).execute(any(), any(), any());

        final RuntimeException thrownException = assertThrows(RuntimeException.class, () -> {
            processor.process();
        });
        assertEquals(thrownException, runtimeException);
    }

    @Test
    void WhenTheInstrumentFiresTheFinishedEventThenTheInstrumentProcessorCallsTheTaskDispatchersFinishedTaskMethodWithTheCorrectTask() {
        final String task = "finished task";
        when(dispatcher.getTask()).thenReturn(task);

        processor.process();

        ArgumentCaptor<Runnable> onFinished = ArgumentCaptor.forClass(Runnable.class);
        verify(instrument).execute(any(), onFinished.capture(), any());
        onFinished.getValue().run();

        verify(dispatcher).finishedTask(task);
    }

    @Test
    void WhenTheInstrumentFiresTheErrorEventThenTheInstrumentProcessorWritesTheStringErrorOccurredToTheConsole() {
        final String task = "failed task";
        when(dispatcher.getTask()).thenReturn(task);

        processor.process();

        ArgumentCaptor<Runnable> onError = ArgumentCaptor.forClass(Runnable.class);
        verify(instrument).execute(any(), any(), onError.capture());
        onError.getValue().run();

        verify(out).println("Error occurred");
    }
}