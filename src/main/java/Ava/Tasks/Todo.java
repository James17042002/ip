package Ava.Tasks;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toDataFormat() {
        String data =  "todo " + description;
        if (isDone) {
            data += "\n" + "mark " + description;
        }
        return data;
    }
}
