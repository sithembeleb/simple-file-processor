package com.file.processor;

import java.io.IOException;

public class FileProcessorApplication {

    public static void main(String[] args) throws IOException {
    FileProcessor fileProcessor = new FileProcessor();
    fileProcessor.processFiles();
    }
}
