import java.util.Scanner;

public class Ava {

    public static final String LINE_SEPARATOR = "_____________________________";

    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        greetings();
        do {
            line = in.nextLine();
            System.out.println(LINE_SEPARATOR);
            System.out.println(line);
            System.out.println(LINE_SEPARATOR);
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
