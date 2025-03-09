package Ava;

import Ava.Exceptions.InvalidDeadlineException;
import Ava.Exceptions.InvalidEventException;
import Ava.Exceptions.InvalidInputException;
import Ava.Exceptions.InvalidTodoException;
import Ava.Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static void parser(Scanner in, ArrayList<Task> list, int counter) {
        String line;
        do {
            line = in.nextLine().toLowerCase();
            try {
                switch (line.split(" ")[0]) {
                case "list":
                    UI.printTaskList(list, counter);
                    break;

                case "mark":
                    TaskList.handleMark(line, list, counter, true);
                    break;

                case "unmark":
                    TaskList.handleUnmark(line, list, counter);
                    break;

                case "bye":
                    // Exit condition: just exit the loop
                    break;

                case "todo":
                    counter = TaskList.addTodo(line, list, counter, true);
                    break;

                case "deadline":
                    counter = TaskList.addDeadline(line, list, counter, true);
                    break;

                case "event":
                    counter = TaskList.addEvent(line, list, counter, true);
                    break;

                case "delete":
                    counter = TaskList.delete(line, list, counter);
                    break;

                default:
                    throw new InvalidInputException("Invalid Input: Please try again with one of the valid commands:\nlist, todo, deadline, event, mark, unmark, delete, bye");
                }
            }
            catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }
            try {
                Storage.saveData(list);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } while (!line.equals("bye"));
    }
}
