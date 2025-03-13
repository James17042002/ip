package Ava;

import Ava.Exceptions.InvalidDeadlineException;
import Ava.Exceptions.InvalidEventException;
import Ava.Exceptions.InvalidInputException;
import Ava.Exceptions.InvalidTodoException;
import Ava.Tasks.Deadline;
import Ava.Tasks.Event;
import Ava.Tasks.Task;
import Ava.Tasks.Todo;

import java.util.ArrayList;

/**
 * The `TaskList` class manages the list of tasks and provides operations to add, delete, and modify tasks.
 */

public class TaskList {

    private static final int THREE_CHAR_WORD = 3;
    private static final int FOUR_CHAR_WORD = 4;
    private static final int FIVE_CHAR_WORD = 5;
    private static final int SIX_CHAR_WORD = 6;
    private static final int EIGHT_CHAR_WORD = 8;

    /**
     * Deletes a task from the task list based on the user input.
     *
     * @param line    The user input containing the task to delete.
     * @param list    The list of tasks.
     * @param counter The current number of tasks in the list.
     * @return The updated number of tasks in the list after deletion.
     */
    public static int delete(String line, ArrayList<Task> list, int counter) {
        String toDelete = line.substring(SIX_CHAR_WORD).trim();
        int index = getTaskIndex(toDelete, list, counter);
        counter--;
        UI.showTaskRemoved(list, counter, index);
        list.remove(index);
        return counter;
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param line      The user input containing the Event details.
     * @param list      The list of tasks.
     * @param counter   The current number of tasks in the list.
     * @param isPrinted Whether to display a confirmation message, since it's used for saving data as well
     * @return The updated number of tasks in the list after adding the Event.
     * @throws InvalidEventException If the Event format is invalid.
     */
    public static int addEvent(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidEventException {
        int fromIndex = line.indexOf("/from");
        if (fromIndex == -1) {
            throw new InvalidEventException("Please use format: event [description] /from [start] /to [end]");
        }
        String event = line.substring(FIVE_CHAR_WORD, fromIndex - 1).trim();
        int toIndex = line.indexOf("/to");
        if (toIndex == -1) {
            throw new InvalidEventException("Please use format: event [description] /from [start] /to [end]");
        }
        String from = line.substring(fromIndex + FIVE_CHAR_WORD, toIndex - 1).trim();
        String to = line.substring(toIndex + 3).trim();

        if (event.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new InvalidEventException("Please use format: event [description] /from [start] /to [end]");
        }

        addTask(new Event(event, from, to), list, counter, isPrinted);
        counter++;
        return counter;
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param line      The user input containing the Deadline details.
     * @param list      The list of tasks.
     * @param counter   The current number of tasks in the list.
     * @param isPrinted Whether to display a confirmation message.
     * @return The updated number of tasks in the list after adding the Deadline.
     * @throws InvalidDeadlineException If the Deadline format is invalid.
     */
    public static int addDeadline(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidDeadlineException {
        int byIndex = line.indexOf("/by");

        if (byIndex == -1) {
            throw new InvalidDeadlineException("Please use format: deadline [description] /by [when]");
        }

        String deadline = line.substring(EIGHT_CHAR_WORD, byIndex - 1).trim();
        String by = line.substring(byIndex + THREE_CHAR_WORD).trim();

        if (deadline.isEmpty() || by.isEmpty()) {
            throw new InvalidDeadlineException("Please use format: deadline [description] /by [when]");
        }
        addTask(new Deadline(deadline, by), list, counter, isPrinted);
        counter++;
        return counter;
    }

    /**
     * Adds a new Todo task to the task list.
     *
     * @param line      The user input containing the Todo details.
     * @param list      The list of tasks.
     * @param counter   The current number of tasks in the list.
     * @param isPrinted Whether to display a confirmation message.
     * @return The updated number of tasks in the list after adding the Todo.
     * @throws InvalidTodoException If the Todo description is empty.
     */
    public static int addTodo(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidTodoException {
        if (line.length() == FOUR_CHAR_WORD) {
            throw new InvalidTodoException("The description of Todo cannot be empty");
        }
        addTask(new Todo(line.substring(FIVE_CHAR_WORD)), list, counter, isPrinted);
        counter++;
        return counter;
    }

    /**
     * Marks a task as done based on the user input.
     *
     * @param line      The user input containing the task to mark.
     * @param list      The list of tasks.
     * @param counter   The current number of tasks in the list.
     * @param isPrinted Whether to display a confirmation message.
     * @throws InvalidInputException If the task to mark is invalid or not found.
     */
    public static void handleMark(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidInputException {
        if (line.length() == FOUR_CHAR_WORD) {
            throw new InvalidInputException("mark/unmark cannot be empty!");
        }
        String toMark = line.substring(line.indexOf(" ") + 1);
        int index = getTaskIndex(toMark, list, counter);

        if (index == -1 && isPrinted) {
            UI.showTaskNotFound(toMark);
        } else {
            list.get(index).setDone();
            if (isPrinted) {
                UI.showTaskMarked(list, index);
            }
        }
    }

    /**
     * Marks a task as not done based on the user input.
     *
     * @param line    The user input containing the task to unmark.
     * @param list    The list of tasks.
     * @param counter The current number of tasks in the list.
     * @throws InvalidInputException If the task to unmark is invalid or not found.
     */
    public static void handleUnmark(String line, ArrayList<Task> list, int counter) throws InvalidInputException {
        if (line.length() == FOUR_CHAR_WORD) {
            throw new InvalidInputException("mark/unmark cannot be empty!");
        }
        String toMark = line.substring(line.indexOf(" ") + 1);
        int index = getTaskIndex(toMark, list, counter);

        if (index == -1) {
            UI.showTaskNotFound(toMark);
        } else {
            list.get(index).setNotDone();
            UI.showTaskUnmarked(list, index);
        }
    }

    /**
     * Finds the index of a task in the list based on the user input.
     *
     * @param toMark  The user input containing the task to find.
     * @param list    The list of tasks.
     * @param counter The current number of tasks in the list.
     * @return The index of the task in the list, or -1 if not found.
     */
    public static int getTaskIndex(String toMark, ArrayList<Task> list, int counter) {
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

    /**
     * Adds a task to the task list and displays a confirmation message if required.
     *
     * @param task      The task to add.
     * @param list      The list of tasks.
     * @param counter   The current number of tasks in the list.
     * @param isPrinted Whether to display a confirmation message.
     */
    public static void addTask(Task task, ArrayList<Task> list, int counter, boolean isPrinted) {
        list.add(task);
        if (isPrinted) {
            UI.showTaskAdded(task, counter);
        }
    }

    /**
     * Finds tasks in the list that contain the specified keyword and displays them to the user.
     *
     * @param list The list of tasks to search through.
     * @param line The user input containing the keyword to search for.
     */
    static void findTasksWithKeyword(ArrayList<Task> list, String line) {
        ArrayList<Task> matchingList = new ArrayList<>(); // List to store matching tasks
        String toFind = line.split(" ")[1]; // Extract the keyword from the user input
        int matchingCounter = 0; // Counter for the number of matching tasks

        // Iterate through the task list to find tasks containing the keyword
        for (Task task : list) {
            if (task.isContains(toFind)) {
                matchingList.add(task); // Add matching tasks to the list
                matchingCounter++; // Increment the matching counter
            }
        }

        // Display a message if no tasks match the keyword
        if (matchingCounter == 0) {
            UI.showTaskNotFound(toFind);
        }

        // Display the list of matching tasks
        UI.printTaskList(matchingList, matchingCounter);
    }
}
