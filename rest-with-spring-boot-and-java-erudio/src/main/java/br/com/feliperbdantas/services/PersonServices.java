package br.com.feliperbdantas.services;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();
}
