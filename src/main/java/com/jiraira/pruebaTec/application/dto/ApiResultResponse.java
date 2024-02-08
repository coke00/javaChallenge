package com.jiraira.pruebaTec.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResultResponse {
    private int status;
    private String message;
    private Object data;
}
