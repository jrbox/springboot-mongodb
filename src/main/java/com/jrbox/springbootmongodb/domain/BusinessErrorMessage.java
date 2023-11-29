package com.jrbox.springbootmongodb.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BusinessErrorMessage {
    private LocalDateTime timestamp;
    private String service;
    private String severity;
    private String code;
    private String reason;
}
