package Ava;

import Ava.Exceptions.InvalidDeadlineException;
import Ava.Exceptions.InvalidEventException;
import Ava.Exceptions.InvalidInputException;
import Ava.Exceptions.InvalidTodoException;
import Ava.Tasks.Deadline;
import Ava.Tasks.Event;
import Ava.Tasks.Task;
import Ava.Tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Ava {

    public static final String LINE_SEPARATOR = "_____________________________";

    private static void loadData(ArrayList<Task> list) throws FileNotFoundException {
        File f = new File("src/main/java/Ava/Data/Ava.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        readData(s, list);
    }

    private static void readData(Scanner in, ArrayList<Task> list) {
        String line;
        int counter = 0;
        while (in.hasNextLine()) {
            line = in.nextLine().toLowerCase();
            try {
                switch (line.split(" ")[0]) {
                case "mark":
                    handleMark(line, list, counter, false);
                    break;

                case "todo":
                    counter = addTodo(line, list, counter, false);
                    break;

                case "deadline":
                    counter = addDeadline(line, list, counter, false);
                    break;

                case "event":
                    counter = addEvent(line, list, counter, false);
                    break;

                default:
                    throw new InvalidInputException("File not formatted correctly!");
                }
            }
            catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void saveData(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter("src/main/java/Ava/Data/Ava.txt");
        for (Task task : list) {
            fw.write(task.toDataFormat());
        }
        fw.close();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        try {
            loadData(list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        int counter = list.size();
        greetings();

        parser(in, list, counter);

        goodbyes();
    }

    private static void parser(Scanner in, ArrayList<Task> list, int counter) {
        String line;
        do {
            line = in.nextLine().toLowerCase();
            try {
                switch (line.split(" ")[0]) {
                case "list":
                    printTaskList(list, counter);
                    break;

                case "mark":
                    handleMark(line, list, counter, true);
                    break;

                case "unmark":
                    handleUnmark(line, list, counter);
                    break;

                case "bye":
                    // Exit condition: just exit the loop
                    break;

                case "todo":
                    counter = addTodo(line, list, counter, true);
                    break;

                case "deadline":
                    counter = addDeadline(line, list, counter, true);
                    break;

                case "event":
                    counter = addEvent(line, list, counter, true);
                    break;

                case "delete":
                    counter = delete(line, list, counter);
                    break;

                default:
                    throw new InvalidInputException("Invalid Input: Please try again with one of the valid commands:\nlist, todo, deadline, event, mark, unmark");
                }
            }
            catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }
            try {
                saveData(list);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } while (!line.equals("bye"));
    }

    private static int delete(String line, ArrayList<Task> list, int counter) {
        String toDelete = line.substring(6).trim();
        int index = getTaskIndex(toDelete, list, counter);
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've removed this task:");
        System.out.println("  " + list.get(index).toString());
        System.out.println("Now you have " + (counter - 1) + " task(s) in the list.");
        System.out.println(LINE_SEPARATOR);
        list.remove(index);
        return counter - 1;
    }

    private static int addEvent(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidEventException {
        int fromIndex = line.indexOf("/from");
        if (fromIndex == -1) {
            throw new InvalidEventException("Please use format: event [description] /from [start] /to [end]");
        }
        String event = line.substring(5, fromIndex - 1).trim();
        int toIndex = line.indexOf("/to");
        if (toIndex == -1) {
            throw new InvalidEventException("Please use format: event [description] /from [start] /to [end]");
        }
        String from = line.substring(fromIndex + 5, toIndex - 1).trim();
        String to = line.substring(toIndex + 3).trim();

        if (event.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidEventException("Please use format: event [description] /from [start] /to [end]");
        }

        addTask(new Event(event, from, to), list, counter, isPrinted);
        counter++;
        return counter;
    }

    private static int addDeadline(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidDeadlineException {
        int byIndex = line.indexOf("/by");

        if (byIndex == -1) {
            throw new InvalidDeadlineException("Please use format: deadline [description] /by [when]");
        }

        String deadline = line.substring(8, byIndex - 1).trim();
        String by = line.substring(byIndex + 3).trim();

        if (deadline.isEmpty() || by.isEmpty()) {
            throw new InvalidDeadlineException("Please use format: deadline [description] /by [when]");
        }
        addTask(new Deadline(deadline, by), list, counter, isPrinted);
        counter++;
        return counter;
    }

    private static int addTodo(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidTodoException {
        if (line.length() == 4) {
            throw new InvalidTodoException("The description of Todo cannot be empty");
        }
        addTask(new Todo(line.substring(5)), list, counter, isPrinted);
        counter++;
        return counter;
    }

    private static void printTaskList(ArrayList<Task> list, int counter) {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ": " + list.get(i).toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleMark(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidInputException {
        if (line.length() == 4) {
            throw new InvalidInputException("mark/unmark cannot be empty!");
        }
        String toMark = line.substring(line.indexOf(" ") + 1);
        int index = getTaskIndex(toMark, list, counter);

        if (index == -1 && isPrinted) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(toMark + " not in list!");
            System.out.println(LINE_SEPARATOR);
        } else {
            list.get(index).setDone();
            if (isPrinted) {
                System.out.println(LINE_SEPARATOR);
                System.out.println("Nice! I've marked this task as done:\n  " + list.get(index));
                System.out.println(LINE_SEPARATOR);
            }
        }
    }

    private static void handleUnmark(String line, ArrayList<Task> list, int counter) throws InvalidInputException {
        if (line.length() == 4) {
            throw new InvalidInputException("mark/unmark cannot be empty!");
        }
        String toMark = line.substring(line.indexOf(" ") + 1);
        int index = getTaskIndex(toMark, list, counter);

        if (index == -1) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(toMark + " not in list!");
            System.out.println(LINE_SEPARATOR);
        } else {
            list.get(index).setNotDone();
            System.out.println(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:\n  " + list.get(index));
            System.out.println(LINE_SEPARATOR);
        }
    }

    private static int getTaskIndex(String toMark, ArrayList<Task> list, int counter) {
        int index = -1;
        if (Character.isDigit(toMark.charAt(0))) {
            index = Integer.parseInt(toMark) - 1;
        } else {
            for (int i = 0; i < counter; i++) {
                if (list.get(i).isTask(toMark)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private static void addTask(Task task, ArrayList<Task> list, int counter, boolean isPrinted) {
        list.add(task);
        if (isPrinted) {
            System.out.println(LINE_SEPARATOR);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task.toString());
            System.out.println("Now you have " + (counter + 1) + " task(s) in the list.");
            System.out.println(LINE_SEPARATOR);
        }
    }

    private static void goodbyes() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    private static void greetings() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm Ava");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }
}
