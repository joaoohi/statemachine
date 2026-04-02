package com.joaoohi.statemachine.service;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageClientServiceTest {

    @Test
    void deveBuscarMensagens() {

        RestTemplate restTemplate = mock(RestTemplate.class);

        when(restTemplate.getForObject(
                "http://localhost:8080/messages",
                String.class))
                .thenReturn("teste");

        MessageClientService service = new MessageClientService(restTemplate);

        service.baseUrl = "http://localhost:8080";

        String resposta = service.buscarMensagens();

        assertEquals("teste", resposta);

        verify(restTemplate)
                .getForObject("http://localhost:8080/messages", String.class);
    }
}