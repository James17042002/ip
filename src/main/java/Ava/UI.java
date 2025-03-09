package Ava;

import Ava.Tasks.Task;

import java.util.ArrayList;

/**li
 * The `UI` class handles all interactions with the user, including displaying messages,
 * task lists, and error messages. It provides a consistent way to format and present
 * information to the user.
 */
public class UI {
    public static final String LINE_SEPARATOR = "_____________________________";

    /**
     * Displays a confirmation message when a task is removed from the list.
     *
     * @param list    The list of tasks.
     * @param counter The current number of tasks in the list.
     * @param index   The index of the task that was removed.
     */
    public static void showTaskRemoved(ArrayList<Task> list, int counter, int index) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've removed this task:");
        System.out.println("  " + list.get(index).toString());
        System.out.println("Now you have " + counter + " task(s) in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param list    The list of tasks to display.
     * @param counter The current number of tasks in the list.
     */
    public static void printTaskList(ArrayList<Task> list, int counter) {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ": " + list.get(i).toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a confirmation message when a task is marked as done.
     *
     * @param list  The list of tasks.
     * @param index The index of the task that was marked.
     */
    public static void showTaskMarked(ArrayList<Task> list, int index) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:\n  " + list.get(index));
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays an error message when a task is not found in the list.
     *
     * @param toMark The task description or identifier that was not found.
     */
    public static void showTaskNotFound(String toMark) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(toMark + " not in list!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a confirmation message when a task is marked as not done.
     *
     * @param list  The list of tasks.
     * @param index The index of the task that was unmarked.
     */
    public static void showTaskUnmarked(ArrayList<Task> list, int index) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:\n  " + list.get(index));
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a confirmation message when a task is added to the list.
     *
     * @param task    The task that was added.
     * @param counter The current number of tasks in the list.
     */
    public static void showTaskAdded(Task task, int counter) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (counter + 1) + " task(s) in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public static void goodbyes() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    /**
     * Displays a greeting message to the user.
     */
    public static void greetings() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm Ava");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }
}
