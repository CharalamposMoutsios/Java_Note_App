import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoteTakerApp {
    private static final String FILENAME = "notes.txt";

    public static void main(String[] args) {
        List<String> notes = loadNotes();

        System.out.println("Welcome to Note Taker!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. View notes");
            System.out.println("2. Add a note");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("----- Your Notes -----");
                    for (int i = 0; i < notes.size(); i++) {
                        System.out.println((i + 1) + ". " + notes.get(i));
                    }
                    System.out.println("-----------------------");
                    break;
                case 2:
                    System.out.print("Enter your note: ");
                    String note = scanner.nextLine();
                    notes.add(note);
                    saveNotes(notes);
                    System.out.println("Note added successfully!");
                    break;
                case 3:
                    System.out.println("Exiting Note Taker. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static List<String> loadNotes() {
        List<String> notes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                notes.add(line);
            }
        } catch (IOException e) {
            // Ignore if file doesn't exist
        }

        return notes;
    }

    private static void saveNotes(List<String> notes) {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            for (String note : notes) {
                writer.write(note + "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving notes.");
        }
    }
}
