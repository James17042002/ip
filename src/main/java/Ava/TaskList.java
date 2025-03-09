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

public class TaskList {
    public static int delete(String line, ArrayList<Task> list, int counter) {
        String toDelete = line.substring(6).trim();
        int index = getTaskIndex(toDelete, list, counter);
        counter--;
        UI.showTaskRemoved(list, counter, index);
        list.remove(index);
        return counter;
    }

    public static int addEvent(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidEventException {
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

    public static int addDeadline(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidDeadlineException {
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

    public static int addTodo(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidTodoException {
        if (line.length() == 4) {
            throw new InvalidTodoException("The description of Todo cannot be empty");
        }
        addTask(new Todo(line.substring(5)), list, counter, isPrinted);
        counter++;
        return counter;
    }

    public static void handleMark(String line, ArrayList<Task> list, int counter, boolean isPrinted) throws InvalidInputException {
        if (line.length() == 4) {
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

    public static void handleUnmark(String line, ArrayList<Task> list, int counter) throws InvalidInputException {
        if (line.length() == 4) {
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

    public static void addTask(Task task, ArrayList<Task> list, int counter, boolean isPrinted) {
        list.add(task);
        if (isPrinted) {
            UI.showTaskAdded(task, counter);
        }
    }
}
