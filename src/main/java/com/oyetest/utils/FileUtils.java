package com.oyetest.utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    public static void writeFile(String outputDirectory, String fileName, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputDirectory + "/" + fileName));
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
