package com.example.texteditorproject.strategy;

public interface EncodingStrategy {
    String decode(byte[] data) throws Exception;
}
