package com.example.user_transfer.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "log")
@Getter
@Setter
@NoArgsConstructor

public class ApplicationLog {
    @Id
    private String id;
    private String method;
    private String payment;
    private LocalDateTime createdTime;


}