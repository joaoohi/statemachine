package com.joaoohi.statemachine.service;

import com.joaoohi.statemachine.enums.Events;
import com.joaoohi.statemachine.enums.States;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ChatService {

    @Autowired
    private StateMachine<States, Events> stateMachine;

    public String receberMensagem(String mensagem) {

        stateMachine.stop();
        stateMachine.start();

        States estadoAtual = stateMachine.getState().getId();

        if (estadoAtual == States.INICIAL) {

            stateMachine.sendEvent(Events.RECEBER_MENSAGEM);

            return getMenu();
        }

        if (estadoAtual == States.AGUARDANDO_RESPOSTA) {

            String resposta = processarOpcao(mensagem);

            stateMachine.sendEvent(Events.ENCERRAR);

            return resposta;
        }

        return "Conversa encerrada.";
    }

    private String getMenu() {
        return """
            1 - Que dia é hoje?
            2 - Quantos dias faltam para o fim do ano?
            3 - Quantos dias já se passaram?
            4 - Sair.
            """;
    }

    private String processarOpcao(String mensagem) {

        switch (mensagem.trim()) {

            case "1":
                return "Hoje é: %s".formatted(LocalDate.now());

            case "2":
                LocalDate hoje = LocalDate.now();
                LocalDate fimAno = LocalDate.of(hoje.getYear(), 12, 31);
                long diasRestantes = ChronoUnit.DAYS.between(hoje, fimAno);
                return "Faltam %d dias para o fim do ano.".formatted(diasRestantes);

            case "3":
                LocalDate inicioAno = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                long diasPassados = ChronoUnit.DAYS.between(inicioAno, LocalDate.now());
                return "Já se passaram %d dias no ano.".formatted(diasPassados);

            case "4":
                return "Conversa encerrada.";

            default:
                return "Opção inválida. Tente novamente.";
        }
    }
}