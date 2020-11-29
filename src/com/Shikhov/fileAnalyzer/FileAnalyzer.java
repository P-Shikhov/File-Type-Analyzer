package com.Shikhov.fileAnalyzer;

import java.io.*;
import java.util.Arrays;

public class FileAnalyzer {
    public static void main(String[] args) {
        FileAnalyzer analyzer = new FileAnalyzer();
        if (!analyzer.hasEnoughArguments(args)){
            System.exit(-1);
        }

        String filePath = args[0];
        String pattern = args[1];
        String fileType = args[2];

        String result = analyzer.getFileTypeNaively(filePath, pattern, fileType);
        System.out.println(result);
    }

    private String getFileTypeNaively(String file, String pattern, String fileType) {
        byte[] bytePattern = pattern.getBytes();
        boolean isPDF = false;

        //todo: check if file is empty: https://stackoverflow.com/questions/10281370/see-if-file-is-empty
//        try (InputStream inputStream = FileAnalyzer.class.getResourceAsStream(file)) {
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
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isPDF ? fileType : "Unknown file type";
    }

    private boolean hasEnoughArguments(String[] args) {
        boolean isEnough = true;
        int requiredLength = 3;
        if (args.length != requiredLength){
            System.out.printf("Please supply %d arguments", requiredLength);
            isEnough = false;
        }
        return isEnough;
    }
}
