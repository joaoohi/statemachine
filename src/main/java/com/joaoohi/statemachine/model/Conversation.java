package com.joaoohi.statemachine.model;

import jakarta.persistence.*;

@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String clientInput;

}