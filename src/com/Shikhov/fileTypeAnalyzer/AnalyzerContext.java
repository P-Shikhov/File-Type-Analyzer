package com.Shikhov.fileTypeAnalyzer;

import java.nio.file.Path;

public class AnalyzerContext {
    private AnalyzerAlgorithm algorithm;
    final private String pattern;
    final private String fileType;

    public void setAlgorithm(AnalyzerAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public AnalyzerContext(String pattern, String fileType) {
        this.pattern = pattern;
        this.fileType = fileType;
    }

    public void analyzeFile(Path filePath) {
            String result = this.algorithm.getFileType(filePath, pattern, fileType);
            System.out.printf("%s: %s %n", filePath.getFileName(), result);
    }
}


