package Ava.Tasks;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataFormat() {
        String data =  "deadline " + description + " /by " + by;
        if (isDone) {
            data += "\n" + "mark " + description;
        }
        return data;
    }
}
