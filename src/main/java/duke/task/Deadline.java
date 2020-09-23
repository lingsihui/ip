package duke.task;

/**
 * Represents an deadline task in Duke. A <code>deadline Task</code> object corresponds to
 * any tasks with a description, a boolean isDone to mark whether task is completed and an
 * by description of when the task need to be completed by
 * e.g., <code>Deadline return book /by sunday</code>
 */
public class Deadline extends Task {
    private static final int BY_LENGTH = 3;
    public static final int DEADLINE_LENGTH = 8;
    protected String by;

    /**
     * Constructor for  Deadline()
     * Initialise description of task, isDone boolean and description of by.
     * Default isDone is false.
     *
     * @param description task description. e.g. return book.
     * @param slashPosition index of the slash position.e.g. sunday.
     */
    public Deadline(String description, int slashPosition){
        super(description.substring(DEADLINE_LENGTH,slashPosition));
        this.by = description.substring(slashPosition + BY_LENGTH);
    }
    /**
     * Returns the strings of the task icon, status icon,description of the task
     * and description of by when task is printed.
     *
     * @return String "[D]" + status icon + task description + by description.
     */
    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + by + ")";
    }
    /**
     * Returns the type of the task as Deadline task.
     * Getter.
     *
     * @return String Deadline.
     */
    public String getType(){
        return "Deadline";
    }
    /**
     * Returns by description for the event.
     * Getter.
     *
     * @return String at. e.g. sunday.
     */
    public String getBy(){
        return this.by;
    }
}
