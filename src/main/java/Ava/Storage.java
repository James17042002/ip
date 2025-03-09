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

public class Storage {
    public static void loadData(ArrayList<Task> list) throws FileNotFoundException {
        File f = new File("src/main/java/Ava/Data/Ava.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        readData(s, list);
    }

    public static void readData(Scanner in, ArrayList<Task> list) {
        String line;
        int counter = 0;
        while (in.hasNextLine()) {
            line = in.nextLine().toLowerCase();
            try {
                switch (line.split(" ")[0]) {
                case "mark":
                    TaskList.handleMark(line, list, counter, false);
                    break;

                case "todo":
                    counter = TaskList.addTodo(line, list, counter, false);
                    break;

                case "deadline":
                    counter = TaskList.addDeadline(line, list, counter, false);
                    break;

                case "event":
                    counter = TaskList.addEvent(line, list, counter, false);
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

    public static void saveData(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter("src/main/java/Ava/Data/Ava.txt");
        for (Task task : list) {
            fw.write(task.toDataFormat() + "\n");
        }
        fw.close();
    }
}
