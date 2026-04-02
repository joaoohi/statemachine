package com.joaoohi.statemachine.controller;

import com.joaoohi.statemachine.service.MessageClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final MessageClientService service;

    public TestController(MessageClientService service) {
        this.service = service;
    }

    @GetMapping("/test-messages")
    public String testar() {
        return service.buscarMensagens();
    }
}