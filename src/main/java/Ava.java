import java.util.Scanner;

public class Ava {

    public static final String LINE_SEPARATOR = "_____________________________";
    public static final int MAX_TASKS = 100;

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);
        String[] list = new String[MAX_TASKS];
        int counter = 0;
        greetings();
        do {
            line = in.nextLine();
            if (!line.equals("list")) {
                list[counter] = line;
                counter++;
                System.out.println(LINE_SEPARATOR);
                System.out.println("added: " + line);
                System.out.println(LINE_SEPARATOR);
            } else {
                System.out.println(LINE_SEPARATOR);
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ": " + list[i]);
                }
                System.out.println(LINE_SEPARATOR);
            }
        } while (!line.equals("bye"));
        goodbyes();
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
