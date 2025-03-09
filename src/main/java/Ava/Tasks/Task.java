package Ava.Tasks;

/**
 * The `Task` class represents a generic task with a description and a completion status.
 * It serves as the base class for specific task types like `Todo`, `Deadline`, and `Event`.
 */
public abstract class Task {
    protected String description; // The description of the task
    protected boolean isDone; // The completion status of the task

    /**
     * Constructs a new `Task` with the given description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false; // By default, tasks are not done
    }

    /**
     * Checks if the task's description matches the given task name.
     *
     * @param task The task name to compare with.
     * @return `true` if the descriptions match, `false` otherwise.
     */
    public boolean isTask(String task) {
        return description.equals(task);
    }

    /**
     * Checks if the task's description contains the given keyword.
     *
     * @param keyword The keyword to search for in the task description.
     * @return `true` if the description contains the keyword, `false` otherwise.
     */
    public boolean isContains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Marks the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, " " (space) otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done tasks with "X"
    }

    /**
     * Checks if this task is equal to another object.
     *
     * @param obj The object to compare with.
     * @return `true` if the objects are equal, `false` otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task t) {
            return description.equals(t.description);
        }
        return false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Converts the task to a data format suitable for saving to a file.
     * This method must be implemented by subclasses.
     *
     * @return A string representing the task in data format.
     */
    public abstract String toDataFormat();
}