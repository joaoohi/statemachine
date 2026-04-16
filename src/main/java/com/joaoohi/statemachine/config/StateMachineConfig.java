package com.joaoohi.statemachine.config;

import com.joaoohi.statemachine.enums.Events;
import com.joaoohi.statemachine.enums.States;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

@Configuration
@EnableStateMachineFactory
public class StateMachineConfig extends StateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.INICIAL)
                .state(States.AGUARDANDO_RESPOSTA)
                .end(States.FINAL);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.INICIAL).target(States.AGUARDANDO_RESPOSTA)
                .event(Events.RECEBER_MENSAGEM)
                .and()
                .withExternal()
                .source(States.AGUARDANDO_RESPOSTA).target(States.FINAL)
                .event(Events.ENCERRAR);
    }
}