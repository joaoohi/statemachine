package com.joaoohi.statemachine.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppStateTest {

    @Test
    void deveConterEstados() {

        AppState[] states = AppState.values();

        assertNotNull(states);
        assertTrue(states.length > 0);
    }
}