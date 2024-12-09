package com.example.texteditorproject.service;

import com.example.texteditorproject.strategy.TextFileDecoder;

import java.io.File;

public class FileService implements TextEditorService{
    private final TextFileDecoder decoder;

    public FileService() {
        this.decoder = new TextFileDecoder();
    }

    public String readFileWithEncoding(File file, String encoding) throws Exception {
        return decoder.decodeFile(file, encoding);
    }
}
