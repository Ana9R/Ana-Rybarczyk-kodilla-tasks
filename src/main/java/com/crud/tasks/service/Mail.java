package com.crud.tasks.service;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

}
