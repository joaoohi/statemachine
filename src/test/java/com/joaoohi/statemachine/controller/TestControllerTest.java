package com.joaoohi.statemachine.controller;

import com.joaoohi.statemachine.service.MessageClientService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TestControllerTest {

    @Test
    void deveRetornarMensagensViaEndpoint() throws Exception {

        MessageClientService service = mock(MessageClientService.class);

        when(service.buscarMensagens()).thenReturn("mensagens");

        TestController controller = new TestController(service);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/test-messages"))
                .andExpect(status().isOk())
                .andExpect(content().string("mensagens"));
    }

    @Test
    void deveRetornarMensagensChamandoMetodoDireto() {

        MessageClientService service = mock(MessageClientService.class);

        when(service.buscarMensagens()).thenReturn("mensagens");

        TestController controller = new TestController(service);

        String resposta = controller.testar();

        assertEquals("mensagens", resposta);

        verify(service).buscarMensagens();
    }
}