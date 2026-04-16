package com.joaoohi.statemachine.model;

import com.joaoohi.statemachine.enums.States;
import jakarta.persistence.*;

@Entity
@Table(name = "conversation_state")
public class ConversationState {

    @Id
    private String clientId;

    @Column(name = "message")
    private String message;

    @Enumerated(EnumType.STRING)
    private States state;




    public ConversationState() {}

    public ConversationState(String clientId, String message, States state) {
        this.clientId = clientId;
        this.message = message;
        this.state = state;
    }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public States getState() { return state; }
    public void setState(States state) { this.state = state; }

}