package com.joaoohi.statemachine.controller;

import com.joaoohi.statemachine.dto.ChatRequest;
import com.joaoohi.statemachine.dto.ChatResponse;
import com.joaoohi.statemachine.service.ChatService;
import com.joaoohi.statemachine.service.MessageClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageClientService messageClientService;

    @PostMapping
    public ChatResponse receber(@RequestBody ChatRequest request) {

        String resposta = chatService.receberMensagem(request.mensagem);

        return new ChatResponse(resposta);
    }

    @GetMapping("/test-cache")
    public String testarCache() {
        return messageClientService.buscarMensagens();
    }
}