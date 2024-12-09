package com.example.texteditorproject.strategy;

import java.nio.charset.StandardCharsets;

public class ISO88591EncodingStrategy implements EncodingStrategy {
    @Override
    public String decode(byte[] data) throws Exception {
        return new String(data, StandardCharsets.ISO_8859_1);
    }
}
