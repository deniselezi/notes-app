package denis.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class FileUtil {
    private FileUtil() {

    }

    public static boolean createFile(String path) {
        // tries to create a file and returns true if it does so successfully
        try {
            File file = new File(path);
            return file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return false;
    }

    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            System.out.println("Deleted the file: " + file.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }

    public static void overwriteFile(String path, String text) {
        try {
            FileWriter writer = new FileWriter(path, false);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendToFile(String path, String lineToAppend) {
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.write(lineToAppend);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            StringBuilder content = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                content.append(line);
                content.append(System.lineSeparator());
                line = br.readLine();
            }
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean renameFile(String oldName, String newName) {
        File file = new File(oldName);
        File rename = new File(newName);
        return file.renameTo(rename);
    }

    public static void replaceLine(String path, String newLine, int lineNumber) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of(path), StandardCharsets.UTF_8));
        System.out.println("at "+lineNumber+" before was " + fileContent.get(lineNumber-1) + " now is " + newLine);
        fileContent.set(lineNumber - 1, newLine);
        Files.write(Path.of(path), fileContent, StandardCharsets.UTF_8);
    }
}
