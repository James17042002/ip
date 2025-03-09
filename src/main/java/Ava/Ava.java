package Ava;

import Ava.Tasks.Task;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileNotFoundException;

/**
 * The main class for the Ava chatbot application.
 */
public class Ava {
    /**
     * The entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        try {
            Storage.loadData(list);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        int counter = list.size();
        UI.greetings();

        Parser.parser(in, list, counter);

        UI.goodbyes();
    }
}
