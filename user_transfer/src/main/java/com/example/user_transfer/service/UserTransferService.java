package com.example.user_transfer.service;


import com.example.user_transfer.controller.dto.AuthenticationRequest;
import com.example.user_transfer.controller.dto.UserDTO;
import com.example.user_transfer.repository.MyEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class UserTransferService {
    private final WebClient webClient;



    public UserTransferService(WebClient.Builder builder){
       this.webClient = builder.baseUrl("http://localhost:8080")
               .build();
    }

    public String authenticate(AuthenticationRequest authenticationRequest)
    {
        return webClient.post()
                .uri("/auth/authenticate")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(authenticationRequest),AuthenticationRequest.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }


    public String transfer(String personalId, Integer idAccountFrom, Integer idAccountTo, double payment, String token)
    {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder
                        .path("/users/transfer")
                        .queryParam("personalId", personalId)
                        .queryParam("idAccountFrom", idAccountFrom)
                        .queryParam("idAccountTo", idAccountTo)
                        .queryParam("payment", payment)
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .headers(h -> h.setBearerAuth(token.substring(7)))
                .retrieve()
                .bodyToMono(String.class)
                .block();



    }



}
