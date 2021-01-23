//package com.Shikhov.fileTypeAnalyzer;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class NaiveAlgorithm implements AnalyzerAlgorithm {
//    @Override
//    String getFileType(String file, String pattern, String fileType) {
//        byte[] bytePattern = pattern.getBytes();
//        boolean isFileType = false;
//
//
//
//        try (InputStream inputStream = new FileInputStream(file)) {
//            int currentByte = inputStream.read();
//            while (currentByte != -1) {
//                if (currentByte == bytePattern[0]) {
//                    byte[] byteArray = new byte[bytePattern.length - 1];
//                    inputStream.read(byteArray);
//                    if (Arrays.equals(byteArray, Arrays.copyOfRange(bytePattern, 1, bytePattern.length))) {
//                        isFileType = true;
//                        break;
//                    }
//                }
//                currentByte = inputStream.read();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return isFileType ? fileType : "Unknown file type";
//    }
//
//    @Override
//    public String getFileType(File file) {
//        return null;
//    }
//}
