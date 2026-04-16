package com.joaoohi.statemachine.config;

import com.joaoohi.statemachine.enums.Events;
import com.joaoohi.statemachine.enums.States;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.service.DefaultStateMachineService;
import org.springframework.statemachine.service.StateMachineService;

@Configuration
public class StateMachineServiceConfig {

    @Bean
    public StateMachineService<States, Events> stateMachineService(
            StateMachineFactory<States, Events> stateMachineFactory) {

        return new DefaultStateMachineService<>(stateMachineFactory);
    }
}