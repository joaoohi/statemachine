package com.joaoohi.statemachine.controller;

import com.joaoohi.statemachine.dto.ConversationRequest;
import com.joaoohi.statemachine.dto.ConversationResponse;
import com.joaoohi.statemachine.model.Conversation;
import com.joaoohi.statemachine.repository.ConversationRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    private final ConversationRepository repository;

    public ConversationController(ConversationRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ConversationResponse iniciarConversa(
            @RequestBody ConversationRequest request) {

        Conversation conversation = new Conversation();
        conversation.clientInput = request.clientInput;

        conversation = repository.save(conversation);

        ConversationResponse response = new ConversationResponse();
        response.id = conversation.id;
        response.clientInput = conversation.clientInput;

        return response;
    }
}