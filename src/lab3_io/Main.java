/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package lab3_io;

/**
 *
 * @author vrund
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Path inputFile = Path.of("people.txt");
        Path professorOut = Path.of("Professor.txt");
        Path studentOut = Path.of("Student.txt");

        List<Professor> professors = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        // 1) Read people.txt, create objects, add to lists
        try (BufferedReader br = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                // Split by any whitespace (tabs/spaces)
                String[] parts = line.split("\\s+");

                // Must have: Title First Last Something...
                if (parts.length < 4) {
                    System.out.println("Skipped (invalid line): " + line);
                    continue;
                }

                String title = parts[0];
                String first = parts[1];
                String last = parts[2];

                // Join the remaining parts (supports multi-word department/degree)
                String detail = String.join(" ", Arrays.copyOfRange(parts, 3, parts.length));

                if (title.equalsIgnoreCase("Professor")) {
                    professors.add(new Professor(first, last, detail));
                } else if (title.equalsIgnoreCase("Student")) {
                    students.add(new Student(first, last, detail));
                } else {
                    System.out.println("Skipped (unknown title): " + line);
                }
            }
        } catch (IOException ex) {
            System.out.println("ERROR reading people.txt");
            System.out.println("Current run folder: " + System.getProperty("user.dir"));
            System.out.println("Details: " + ex.getMessage());
            return;
        }

        // 2) Print both lists to standard output (required)
        System.out.println("\n--- PROFESSORS (" + professors.size() + ") ---");
        for (Professor p : professors) {
            System.out.println(p);
        }

        System.out.println("\n--- STUDENTS (" + students.size() + ") ---");
        for (Student s : students) {
            System.out.println(s);
        }

        // 3) Write to Professor.txt and Student.txt (required)
        try (BufferedWriter profWriter = Files.newBufferedWriter(professorOut, StandardCharsets.UTF_8);
             BufferedWriter studentWriter = Files.newBufferedWriter(studentOut, StandardCharsets.UTF_8)) {

            for (Professor p : professors) {
                profWriter.write(p.toRecordLine());
                profWriter.newLine();
            }

            for (Student s : students) {
                studentWriter.write(s.toRecordLine());
                studentWriter.newLine();
            }

            System.out.println("\nWrote files: " + professorOut + " and " + studentOut);

        } catch (IOException ex) {
            System.out.println("ERROR writing output files: " + ex.getMessage());
        }
    }
}
