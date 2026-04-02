package com.joaoohi.statemachine.service;

import com.joaoohi.statemachine.enums.Events;
import com.joaoohi.statemachine.enums.States;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.statemachine.StateMachine;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ChatServiceTest {

    private ChatService chatService;
    private StateMachine<States, Events> stateMachine;

    @BeforeEach
    void setup() {

        stateMachine = mock(StateMachine.class);
        chatService = new ChatService();

        ReflectionTestUtils.setField(chatService, "stateMachine", stateMachine);
    }

    @Test
    void deveRetornarMenuQuandoEstadoInicial() {

        var state = mock(org.springframework.statemachine.state.State.class);

        when(stateMachine.getState()).thenReturn(state);
        when(state.getId()).thenReturn(States.INICIAL);

        List<String> resposta = chatService.receberMensagem("oi");

        assertEquals(4, resposta.size());

        verify(stateMachine).start();
        verify(stateMachine).sendEvent(Events.RECEBER_MENSAGEM);
    }

    @Test
    void deveRetornarEstadoAtualQuandoNaoForInicial() {

        var state = mock(org.springframework.statemachine.state.State.class);

        when(stateMachine.getState()).thenReturn(state);
        when(state.getId()).thenReturn(States.FINAL);

        List<String> resposta = chatService.receberMensagem("oi");

        assertTrue(resposta.get(0).contains("Estado atual"));

        verify(stateMachine).start();
    }

    @Test
    void deveGerarIdsIncrementais() {

        Long id1 = chatService.gerarId();
        Long id2 = chatService.gerarId();

        assertEquals(1, id1);
        assertEquals(2, id2);
    }
}