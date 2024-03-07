package com.jiraira.pruebaTec.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    private Integer id;
    private String name;
}
