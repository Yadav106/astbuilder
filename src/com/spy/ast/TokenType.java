package com.spy.ast;

public enum TokenType {
    // Single-character tokens
    LEFT_PAREN, RIGHT_PAREN, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // One or Two character tokens
    BANG, BANG_EQUAL, EQUAL, EQUAL_EQUAL, GREATER, GREATER_EQUAL, LESS, LESS_EQUAL,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    EOF
}
