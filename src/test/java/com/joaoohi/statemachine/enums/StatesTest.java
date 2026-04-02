package com.joaoohi.statemachine.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatesTest {

    @Test
    void deveConterEstadoInicial() {

        States state = States.INICIAL;

        assertEquals("INICIAL", state.name());
    }

    @Test
    void deveListarTodosEstados() {

        States[] states = States.values();

        assertTrue(states.length > 0);
    }
}