import java.util.Scanner;

public class Ava {

    public static final String LINE_SEPARATOR = "_____________________________";
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        Task[] list = new Task[MAX_TASKS];
        int counter = 0;
        greetings();

        do {
            line = in.nextLine().toLowerCase();
            try {
                switch (line.split(" ")[0]) {
                case "list":
                    printTaskList(list, counter);
                    break;

                case "mark":
                    handleMark(line, list, counter);
                    break;

                case "unmark":
                    handleUnmark(line, list, counter);
                    break;

                case "bye":
                    // Exit condition: just exit the loop
                    break;

                case "todo":
                    counter = addTodo(line, list, counter);
                    break;

                case "deadline":
                    counter = addDeadline(line, list, counter);
                    break;

                case "event":
                    counter = addEvent(line, list, counter);
                    break;

                default:
                    throw new InvalidInputException("Invalid Input: Please try again with one of the valid commands:\nlist, todo, deadline, event, mark, unmark");
                }
            }
            catch (InvalidInputException | InvalidTodoException | InvalidDeadlineException | InvalidEventException e) {
                System.out.println(e.getMessage());
            }

        } while (!line.equals("bye"));

        goodbyes();
    }

    private static int addEvent(String line, Task[] list, int counter) throws InvalidEventException {
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

        addTask(new Event(event, from, to), list, counter);
        counter++;
        return counter;
    }

    private static int addDeadline(String line, Task[] list, int counter) throws InvalidDeadlineException {
        int byIndex = line.indexOf("/by");

        if (byIndex == -1) {
            throw new InvalidDeadlineException("Please use format: deadline [description] /by [when]");
        }

        String deadline = line.substring(8, byIndex - 1).trim();
        String by = line.substring(byIndex + 3).trim();

        if (deadline.isEmpty() || by.isEmpty()) {
            throw new InvalidDeadlineException("Please use format: deadline [description] /by [when]");
        }
        addTask(new Deadline(deadline, by), list, counter);
        counter++;
        return counter;
    }

    private static int addTodo(String line, Task[] list, int counter) throws InvalidTodoException {
        if (line.length() == 4) {
            throw new InvalidTodoException("The description of Todo cannot be empty");
        }
        addTask(new Todo(line.substring(5)), list, counter);
        counter++;
        return counter;
    }

    private static void printTaskList(Task[] list, int counter) {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ": " + list[i].toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    private static void handleMark(String line, Task[] list, int counter) throws InvalidInputException {
        String toMark = line.substring(line.indexOf(" ") + 1);
        if (toMark.isEmpty()) {
            throw new InvalidInputException("mark/unmark cannot be empty!");
        }
        int index = getTaskIndex(toMark, list, counter);

        if (index == -1) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(toMark + " not in list!");
            System.out.println(LINE_SEPARATOR);
        } else {
            list[index].setDone();
            System.out.println(LINE_SEPARATOR);
            System.out.println("Nice! I've marked this task as done:\n  " + list[index]);
            System.out.println(LINE_SEPARATOR);
        }
    }

    private static void handleUnmark(String line, Task[] list, int counter) {
        String toMark = line.substring(line.indexOf(" ") + 1);
        int index = getTaskIndex(toMark, list, counter);

        if (index == -1) {
            System.out.println(LINE_SEPARATOR);
            System.out.println(toMark + " not in list!");
            System.out.println(LINE_SEPARATOR);
        } else {
            list[index].setNotDone();
            System.out.println(LINE_SEPARATOR);
            System.out.println("OK, I've marked this task as not done yet:\n  " + list[index]);
            System.out.println(LINE_SEPARATOR);
        }
    }

    private static int getTaskIndex(String toMark, Task[] list, int counter) {
        int index = -1;
        if (Character.isDigit(toMark.charAt(0))) {
            index = Integer.parseInt(toMark) - 1;
        } else {
            for (int i = 0; i < counter; i++) {
                if (list[i].isTask(toMark)) {
                    index = i;
                    break;
                }
            }
        }
        return index;
    }

    private static void addTask(Task task, Task[] list, int counter) {
        list[counter] = task;
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (counter + 1) + " task(s) in the list.");
        System.out.println(LINE_SEPARATOR);
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
