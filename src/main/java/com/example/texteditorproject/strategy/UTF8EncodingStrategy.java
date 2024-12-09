package com.example.texteditorproject.strategy;

import java.nio.charset.StandardCharsets;

public class UTF8EncodingStrategy implements EncodingStrategy{

    @Override
    public String decode(byte[] data) throws Exception {
        return new String(data, StandardCharsets.UTF_8);
    }
}
