package com.bench.practice.cloud.gateway;

import org.springframework.web.server.ResponseStatusException;

import java.util.function.Predicate;

public class HttpInternalServicePredicate implements Predicate<ResponseStatusException> {
    @Override
    public boolean test(ResponseStatusException e) {
        return e.getStatus().is5xxServerError();
    }
}
