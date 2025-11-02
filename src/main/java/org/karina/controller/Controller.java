package org.karina.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.karina.exception.ErrorResponse;
import org.karina.factory.PrimeNumberCalculatorType;
import org.karina.model.Response;
import org.karina.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private CalculatorService calculatorService;

    @Operation(summary = "Calculate all primes up to and including n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of primes calculated up to and including n",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Response.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid n or algorithm",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class)) }) })
    @GetMapping(path = "/calculate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> getPrimes(@RequestParam Integer n, @RequestParam Optional<PrimeNumberCalculatorType> algorithm) {
        Response response = calculatorService.getResponse(n, algorithm.orElse(PrimeNumberCalculatorType.ERATOSTHENES));
        return ResponseEntity.ok(response);
    }
}
