package com.Shikhov.fileTypeAnalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helpers {

    public static List<Path> getFilePaths(Path baseDirectory) {
        List<Path> filePaths = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(baseDirectory)) {
            filePaths = walk.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePaths;
    }

    public static boolean hasEnoughArguments(String[] args) {
        boolean isEnough = true;
        int requiredLength = 3;
        if (args.length != requiredLength){
            System.out.printf("Please supply %d arguments", requiredLength);
            isEnough = false;
        }
        return isEnough;
    }

}
