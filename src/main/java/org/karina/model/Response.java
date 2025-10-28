package org.karina.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Response {
    private Integer n;
    private List<Integer> primeNumberList;
    private String algorithm;
}
