package com.Shikhov.fileTypeAnalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class KMPAlgorithm implements AnalyzerAlgorithm {
    @Override
    public String getFileType(Path filepath, String pattern, String fileType) {
        byte[] binPattern = pattern.getBytes();
        boolean isNeededFile = false;
        try {
            byte[] binFile = Files.readAllBytes(filepath);
            int[] prefixFunc = prefixFunction(pattern);
            int j = 0;
            for (byte b : binFile) {
                while (j > 0 && b != binPattern[j]) {
                    j = prefixFunc[j - 1];
                }
                if (b == binPattern[j]) {
                    j += 1;
                }
                if (j == binPattern.length) {
                    isNeededFile = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isNeededFile ? fileType : "Unknown file type";
    }

    private static int[] prefixFunction(String str) {
        int[] prefixFunc = new int[str.length()];
        for (int i = 1; i < str.length(); i++) {
            int j = prefixFunc[i - 1];
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = prefixFunc[j - 1];
            }
            if (str.charAt(i) == str.charAt(j)) {
                j += 1;
            }
            prefixFunc[i] = j;
        }
        return prefixFunc;
    }
}
