package com.Shikhov.fileTypeAnalyzer;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        AnalyzerContext context = new AnalyzerContext();
        if (!hasEnoughArguments(args)) {
            System.exit(-1);
        }

        String algorithm = args[0];
        String filePath = args[1];
        String pattern = args[2];
        String fileType = args[3];
        System.out.println(algorithm);
        switch (algorithm) {
            case "--naive":
                context.setAlgorithm(new NaiveAlgorithm());
            case "--KMP":
                context.setAlgorithm(new KMPAlgorithm());
        }
        String resultFileType = context.defineFileType(filePath, pattern, fileType);
        System.out.println(resultFileType);
        double elapsedNanos = (double) (System.nanoTime() - startTime) / 1_000_000_000;
        System.out.printf("It took %.4f seconds%n", elapsedNanos);
    }

    private static boolean hasEnoughArguments(String[] args) {
        boolean isEnough = true;
        int requiredLength = 4;
        if (args.length != requiredLength){
            System.out.printf("Please supply %d arguments", requiredLength);
            isEnough = false;
        }
        return isEnough;
    }
}
