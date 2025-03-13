package Ava;

import Ava.Exceptions.InvalidDeadlineException;
import Ava.Exceptions.InvalidEventException;
import Ava.Exceptions.InvalidInputException;
import Ava.Exceptions.InvalidTodoException;
import Ava.Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Storage` class handles loading tasks from a file and saving tasks to a file.
 * It interacts with the `TaskList` class to manage the task data.
 */
public class Storage {

    /**
     * Loads tasks from the file into the provided task list.
     *
     * @param list The list of tasks to populate with data from the file.
     * @throws FileNotFoundException If the file is not found at the specified path.
     */
    public static void loadData(ArrayList<Task> list) throws FileNotFoundException {
        File f = new File("Ava.txt"); // Create a File for the given file path
        Scanner s = new Scanner(f); // Create a Scanner using the File as the source
        readData(s, list);
    }

    /**
     * Reads data from the file and populates the task list.
     *
     * @param in   The scanner to read the file.
     * @param list The list of tasks to populate.
     */
    public static void readData(Scanner in, ArrayList<Task> list) {
        String line;
        int counter = 0;
        while (in.hasNextLine()) {
            line = in.nextLine().toLowerCase();
            try {
                switch (line.split(" ")[0]) {
                case "mark":
                    // Marks a task as done
                    TaskList.handleMark(line, list, counter, false);
                    break;

                case "todo":
                    // Adds a Todo task
                    counter = TaskList.addTodo(line, list, counter, false);
                    break;

                case "deadline":
                    // Adds a Deadline task
                    counter = TaskList.addDeadline(line, list, counter, false);
                    break;

                case "event":
                    // Adds an Event task
                    counter = TaskList.addEvent(line, list, counter, false);
                    break;

                default:
                    // Throws an exception for invalid file formats
                    throw new InvalidInputException("File not formatted correctly!");
                }
            } catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                // Handles exceptions and displays error messages
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Saves the current task list to the file.
     *
     * @param list The list of tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void saveData(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter("Ava.txt");
        for (Task task : list) {
            fw.write(task.toDataFormat() + "\n"); // Write each task in data format to the file
        }
        fw.close();
    }
}
