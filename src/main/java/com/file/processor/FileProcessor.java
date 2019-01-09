package com.file.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileProcessor {

    private static final Logger log = LoggerFactory.getLogger(FileProcessor.class);
    private ExecutorService executor =  Executors.newFixedThreadPool(5);

    public void processFiles() throws IOException {
        Path relativePath = Paths.get("src/main/resources/files");
        String path = relativePath.toAbsolutePath().toString();
        File fileDirectory = new File(path);
        if(fileDirectory.isDirectory() && fileDirectory.list().length > 0){
            List<String> fileList = Arrays.asList(fileDirectory.list());
            fileList.forEach(file -> executor.execute(() -> readFile(path, file)));
        }
    }

    private void readFile(final String path, final String fileName) {
         int transaction = 0;
         int tracking = 0;
         int termination = 0;
         int file = 0;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(String.format("%s/%s", path, fileName)))) {
            while ((line = br.readLine()) != null ) {
                switch (line) {
                    case "TRANSACTION":
                        transaction++;
                        break;
                    case "TRACKING":
                        tracking++;
                        break;
                    case "FILE":
                        file++;
                        break;
                    case "TERMINATION":
                        termination++;
                        break;
                    default:
                        break;
                }
            }
            ProcessingReport processingReport = new ProcessingReport(transaction, tracking, termination, file, fileName);
            DbConnection.connectToDatabase(processingReport);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
