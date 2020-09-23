package duke.task;

/**
 * Represents a task in Duke. A <code>Task</code> object corresponds to
 * any tasks with a description and a boolean isDone to mark whether task is completed.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task()
     * Initialise description of task and isDone boolean.
     * Default isDone is false.
     *
     * @param description task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns an Icon corresponding to the boolean isDone.
     * If isDone is false, returns icon CROSS_ICON.
     * If isDone is true, returns icon TICK_ICON.
     *
     * @return IsDone Icon
     */
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }
    /**
     * Returns boolean isDone.
     * Getter.
     *
     * @return Boolean IsDone
     */
    public boolean getIsDone(){
        return isDone;
    }
    /**
     * Set isDone of a task to be true when this function is called.
     * Setter.
     */
    public void markAsDone(){
        this.isDone = true;
    }
    /**
     * Returns empty string as general task do not have a deadline.
     * Getter.
     *
     * @return String " "
     */
    public String getBy(){
        return " ";
    }
    /**
     * Returns empty string as general task do not have a date.
     * Getter.
     *
     * @return String " "
     */
    public String getAt(){
        return " ";
    }
    /**
     * Returns the strings of the status icon and description of the task when task is printed.
     *
     * @return String status icon + task description.
     */
    @Override
    public String toString(){
        return getStatusIcon() + description;
    }
    /**
     * Returns the description of a task.
     *
     * @return String description.
     */
    public String getDescription(){
        return this.description;
    }
    /**
     * Returns the type of a task.
     *
     * @return String description.
     */
    public abstract String getType();
    /**
     * Returns the length of a description.
     *
     * @return int description length.
     */
    public int getDescriptionLength() {
        return description.length();
    }
}
