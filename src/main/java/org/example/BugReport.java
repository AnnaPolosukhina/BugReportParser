package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BugReport {

    public static void main(String[] args) {

        String inputFilePath = "D:\\files\\input.txt";  // имя входного файла
        String outputFilePath = "D:\\files\\output.txt";  // имя выходного файла

        try {
            // Чтение входного файла
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            List<String> entries = new ArrayList<>();
            StringBuilder currentEntry = new StringBuilder();
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Удаляем лишние пробелы

                if (line.matches("^\\d+\\..*")) { // Если строка начинается с номера
                    if (currentEntry.length() > 0) {
                        entries.add(currentEntry.toString().replace("\n", " <br/>").trim());
                        currentEntry.setLength(0);
                    }
                }

                if (currentEntry.length() > 0) {
                    currentEntry.append("\n");
                }
                currentEntry.append(line);
            }

            if (currentEntry.length() > 0) {
                entries.add(currentEntry.toString().replace("\n", "<br/>").trim());
            }

            reader.close();

            // Запись в выходной файл
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            writer.write("<details>\n<summary>Баг репорт №1 </summary>\n\n");
            writer.write("| № | проверка | результат |\n");
            writer.write("| --- | --- | --- |\n");

            for (int i = 0; i < entries.size(); i++) {
                writer.write("| " + (i + 1) + " | " + entries.get(i).replaceAll("^[\\d]+\\.\\s*", "") + " |  |\n");
            }

            writer.write("\n</details>");
            writer.close();

            System.out.println("file output.txt created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

