package com.Shikhov.fileTypeAnalyzer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        if (!Helpers.hasEnoughArguments(args)) {
            System.exit(-1);
        }

        Path baseDirectory = Paths.get(args[0]);
        String pattern = args[1];
        String fileType = args[2];

        AnalyzerContext context = new AnalyzerContext(pattern, fileType);
        context.setAlgorithm(new KMPAlgorithm());
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Path> filePaths = Helpers.getFilePaths(baseDirectory);
        for (Path filePath : filePaths) {
            executorService.submit(() -> {
                context.analyzeFile(filePath);
            });
        }
    }
}
