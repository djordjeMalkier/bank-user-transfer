package com.example.user_transfer.controller;

import com.example.user_transfer.controller.dto.AuthenticationRequest;
import com.example.user_transfer.service.UserTransferService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserTransferController {


   private final UserTransferService userTransferService;





    @PostMapping("/authenticate")
    public String authenticate(@RequestBody AuthenticationRequest request) {
       return userTransferService.authenticate(request);

    }

    @PutMapping("/transfer")
    public ResponseEntity<String> transferFromUserAccountToAnotherUserAccount(@RequestParam String personalId,
                                                                              @RequestParam Integer idAccountFrom,
                                                                              @RequestParam Integer idAccountTo,
                                                                              @RequestParam Double payment,
                                                                              @RequestHeader (HttpHeaders.AUTHORIZATION) String token) {

            String transferMoney = userTransferService.transfer(personalId,idAccountFrom,idAccountTo,payment,token);
            return ok(transferMoney);

    }

}
