package com.Shikhov.fileTypeAnalyzer;

import java.nio.file.Path;

public interface AnalyzerAlgorithm {
    String getFileType(Path filepath, String pattern, String fileType);
}
