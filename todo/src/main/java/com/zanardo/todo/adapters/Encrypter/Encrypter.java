package com.zanardo.todo.adapters.Encrypter;

public interface Encrypter {
    String encode(String rawPassword);
    boolean compare(String rawPassword, String encodedPassword);
}
