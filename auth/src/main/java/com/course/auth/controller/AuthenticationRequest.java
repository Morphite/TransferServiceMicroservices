package com.course.auth.controller;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private final String login;
    private final String pass;
}
