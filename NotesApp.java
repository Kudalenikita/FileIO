import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes Manager ---");
            System.out.println("1. Write a note");
            System.out.println("2. Read all notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    writeNote(scanner);
                    break;
                case 2:
                    readNotes();
                    break;
                case 3:
                    System.out.println("Exiting Notes Manager. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        scanner.close();
    }

    private static void writeNote(Scanner scanner) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // true for append mode
            System.out.print("Enter your note: ");
            String note = scanner.nextLine();
            writer.write(note + "\n");
            System.out.println("Note saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing the note.");
            e.printStackTrace();
        }
    }

    private static void readNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- All Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No notes found. Start writing one!");
        } catch (IOException e) {
            System.out.println("An error occurred while reading notes.");
            e.printStackTrace();
        }
    }
}
