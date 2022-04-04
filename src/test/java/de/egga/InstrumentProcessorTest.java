package de.egga;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InstrumentProcessorTest {

    @Mock
    Instrument instrument;

    @Mock
    TaskDispatcher dispatcher;

    @InjectMocks
    InstrumentProcessor processor;

}