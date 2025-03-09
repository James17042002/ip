package Ava.Tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isTask(String task) {
        return description.equals(task);
    }

    public boolean isContains(String keyword) {
        return description.contains(keyword);
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean equals(Object obj) {
        if (obj instanceof Task t) {
            return description.equals(t.description);
        }
        return false;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toDataFormat();
}
