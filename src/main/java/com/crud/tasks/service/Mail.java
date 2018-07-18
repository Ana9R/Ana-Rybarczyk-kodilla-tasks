package com.crud.tasks.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

}
