package br.com.feliperbdantas.interfaces.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {}
