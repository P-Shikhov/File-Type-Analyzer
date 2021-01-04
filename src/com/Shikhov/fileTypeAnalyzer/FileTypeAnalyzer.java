package com.Shikhov.fileTypeAnalyzer;

import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;

class AnalyzerContext {
    private AnalyzerAlgorithm algorithm;

    public void setAlgorithm(AnalyzerAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public String defineFileType(String file, String pattern, String fileType) {
        return this.algorithm.getFileType(file, pattern, fileType);
    }
}

interface AnalyzerAlgorithm {
    String getFileType(String file, String pattern, String fileType);
}

class NaiveAlgorithm implements AnalyzerAlgorithm {
    @Override
    public String getFileType(String file, String pattern, String fileType) {
        byte[] bytePattern = pattern.getBytes();
        boolean isPDF = false;

        //todo: check if file is empty: https://stackoverflow.com/questions/10281370/see-if-file-is-empty
        try (InputStream inputStream = new FileInputStream(file)) {
            int currentByte = inputStream.read();
            while (currentByte != -1) {
                if (currentByte == bytePattern[0]) {
                    byte[] byteArray = new byte[bytePattern.length - 1];
                    inputStream.read(byteArray);
                    if (Arrays.equals(byteArray, Arrays.copyOfRange(bytePattern, 1, bytePattern.length))) {
                        isPDF = true;
                        break;
                    }
                }
                currentByte = inputStream.read();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isPDF ? fileType : "Unknown file type";
    }
}

class KMPAlgorithm implements AnalyzerAlgorithm {
    @Override
    public String getFileType(String file, String pattern, String fileType) {
        File fileObject = new File(file);
        byte[] binPattern = pattern.getBytes();
        boolean isNeededFile = false;
        try {
            byte[] binFile = Files.readAllBytes(fileObject.toPath());
            int[] prefixFunc = prefixFunction(pattern);
            int j = 0;
            for (int i = 0; i < binFile.length; i++) {
                while (j > 0 && binFile[i] != binPattern[j]) {
                    j = prefixFunc[j - 1];
                }
                if (binFile[i] == binPattern[j]) {
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
