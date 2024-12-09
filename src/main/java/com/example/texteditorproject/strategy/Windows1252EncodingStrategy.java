package com.example.texteditorproject.strategy;

import java.nio.charset.Charset;

public class Windows1252EncodingStrategy implements EncodingStrategy {
    @Override
    public String decode(byte[] data) {
        return new String(data, Charset.forName("Windows-1252"));
    }
}
