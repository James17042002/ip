package Ava;

import Ava.Exceptions.InvalidDeadlineException;
import Ava.Exceptions.InvalidEventException;
import Ava.Exceptions.InvalidInputException;
import Ava.Exceptions.InvalidTodoException;
import Ava.Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Parser` class is responsible for interpreting user commands and delegating
 * the corresponding actions to the appropriate classes (e.g., `TaskList`, `UI`, `Storage`).
 */
public class Parser {

    /**
     * Parses user input and executes the corresponding command.
     *
     * @param in      The scanner to read user input.
     * @param list    The list of tasks to be modified or displayed.
     * @param counter The current number of tasks in the list.
     */
    public static void parser(Scanner in, ArrayList<Task> list, int counter) {
        String line;
        do {
            line = in.nextLine().toLowerCase();
            try {
                switch (line.split(" ")[0]) {
                case "list":
                    // Displays the list of tasks
                    UI.printTaskList(list, counter);
                    break;

                case "mark":
                    // Marks a task as done
                    TaskList.handleMark(line, list, counter, true);
                    break;

                case "unmark":
                    // Marks a task as not done
                    TaskList.handleUnmark(line, list, counter);
                    break;

                case "bye":
                    // Exits the application
                    break;

                case "todo":
                    // Adds a new Todo task
                    counter = TaskList.addTodo(line, list, counter, true);
                    break;

                case "deadline":
                    // Adds a new Deadline task
                    counter = TaskList.addDeadline(line, list, counter, true);
                    break;

                case "event":
                    // Adds a new Event task
                    counter = TaskList.addEvent(line, list, counter, true);
                    break;

                case "delete":
                    // Deletes a task from the list
                    counter = TaskList.delete(line, list, counter);
                    break;

                case "find":
                    TaskList.findTasksWithKeyword(list, line);
                    break;

                default:
                    // Throws an exception for invalid commands
                    throw new InvalidInputException("Invalid Input: Please try again with one of the valid commands:\n"
                            + "list, todo, deadline, event, mark, unmark, delete, bye");
                }
            } catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                // Handles exceptions and displays error messages
                System.out.println(e.getMessage());
            }

            try {
                // Saves the updated task list to the file
                Storage.saveData(list);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } while (!line.equals("bye")); // Continues until the user enters "bye"
    }
}