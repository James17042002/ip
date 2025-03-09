package Ava;

import Ava.Tasks.Task;

import java.util.ArrayList;

public class UI {
    public static final String LINE_SEPARATOR = "_____________________________";

    public static void showTaskRemoved(ArrayList<Task> list, int counter, int index) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've removed this task:");
        System.out.println("  " + list.get(index).toString());
        System.out.println("Now you have " + counter + " task(s) in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void printTaskList(ArrayList<Task> list, int counter) {
        System.out.println(LINE_SEPARATOR);
        for (int i = 0; i < counter; i++) {
            System.out.println((i + 1) + ": " + list.get(i).toString());
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void showTaskMarked(ArrayList<Task> list, int index) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Nice! I've marked this task as done:\n  " + list.get(index));
        System.out.println(LINE_SEPARATOR);
    }

    public static void showTaskNotFound(String toMark) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(toMark + " not in list!");
        System.out.println(LINE_SEPARATOR);
    }

    public static void showTaskUnmarked(ArrayList<Task> list, int index) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("OK, I've marked this task as not done yet:\n  " + list.get(index));
        System.out.println(LINE_SEPARATOR);
    }

    public static void showTaskAdded(Task task, int counter) {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + (counter + 1) + " task(s) in the list.");
        System.out.println(LINE_SEPARATOR);
    }

    public static void goodbyes() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE_SEPARATOR);
    }

    public static void greetings() {
        System.out.println(LINE_SEPARATOR);
        System.out.println("Hello! I'm Ava");
        System.out.println("What can I do for you?");
        System.out.println(LINE_SEPARATOR);
    }
}
