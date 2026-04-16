package com.joaoohi.statemachine.service;

import com.joaoohi.statemachine.enums.States;
import com.joaoohi.statemachine.model.ConversationState;
import com.joaoohi.statemachine.repository.ConversationStateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ChatService {

    private final ConversationStateRepository repository;

    public ChatService(ConversationStateRepository repository) {
        this.repository = repository;
    }

    public String receberMensagem(String clientId, String mensagem) {

        ConversationState conv = repository.findById(clientId)
                .orElse(new ConversationState(clientId, mensagem, States.INICIAL));

        States estadoAtual = conv.getState() != null ? conv.getState() : States.INICIAL;

        if (estadoAtual == States.FINAL) {
            conv.setState(States.INICIAL);
            estadoAtual = States.INICIAL;
        }

        if (estadoAtual == States.INICIAL) {
            conv.setState(States.AGUARDANDO_RESPOSTA);
            conv.setMessage(mensagem);
            repository.save(conv);
            return getMenu();

        } else if (estadoAtual == States.AGUARDANDO_RESPOSTA) {
            String resposta = processarOpcao(mensagem);
            conv.setState(States.FINAL);
            conv.setMessage(mensagem);
            repository.save(conv);
            return resposta;
        }

        return "Estado inválido da conversa.";
    }

    private String getMenu() {
        return """
            Escolha uma opção:
            1 - Que dia é hoje?
            2 - Quantos dias faltam para o fim do ano?
            3 - Quantos dias já se passaram no ano?
            4 - Sair.
            """;
    }

    private String processarOpcao(String mensagem) {
        return switch (mensagem.trim()) {
            case "1" -> "Hoje é: %s".formatted(LocalDate.now());
            case "2" -> {
                LocalDate hoje = LocalDate.now();
                LocalDate fimAno = LocalDate.of(hoje.getYear(), 12, 31);
                long dias = ChronoUnit.DAYS.between(hoje, fimAno);
                yield "Faltam %d dias para o fim do ano.".formatted(dias);
            }
            case "3" -> {
                LocalDate inicio = LocalDate.of(LocalDate.now().getYear(), 1, 1);
                long dias = ChronoUnit.DAYS.between(inicio, LocalDate.now());
                yield "Já se passaram %d dias no ano.".formatted(dias);
            }
            case "4" -> "Conversa encerrada.";
            default -> "Opção inválida. Escolha entre 1, 2, 3 ou 4.";
        };
    }
}