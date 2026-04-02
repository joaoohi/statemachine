package com.joaoohi.statemachine.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MessageClientService {

    private final RestTemplate restTemplate;

    @Value("${message.api.base-url}")
    String baseUrl;

    public MessageClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("mensagens")
    public String buscarMensagens() {

        System.out.println("Chamando API de mensagens...");

        String url = baseUrl + "/messages";
        return restTemplate.getForObject(url, String.class);
    }
}