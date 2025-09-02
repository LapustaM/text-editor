package com.example.texteditorproject.service;

import com.example.texteditorproject.strategy.TextFileDecoder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileService implements TextEditorService{
    private final TextFileDecoder decoder;

    public FileService() {
        this.decoder = new TextFileDecoder();
    }

    public String readFileWithEncoding(File file, String encoding) throws Exception {
        return decoder.decodeFile(file, encoding);
    }
    public void writeFileWithEncoding(File file, String content, String encoding) throws IOException {
        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
