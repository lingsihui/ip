package duke.task;

/**
 * Represents a to-do task in Duke. A <code>to-do Task</code> object corresponds to
 * any tasks with a description and a boolean isDone to mark whether task is completed.
 * e.g., <code>Todo read book</code>
 */
public class Todo extends Task {
    public static final int TODO_LENGTH = 4;

    /**
     * Constructor for To-do Task()
     * Initialise description of task and isDone boolean.
     * Default isDone is false.
     *
     * @param description task description. e.g. read book.
     */
    public Todo(String description){
        super(description.substring(TODO_LENGTH));
    }
    /**
     * Returns the strings of the task icon, status icon and description of the task when task is printed.
     *
     * @return String "[T]" + status icon + task description.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
    /**
     * Returns the type of the task as To-do task.
     * Getter.
     *
     * @return String To-do task.
     */
    public String getType(){
        return "Todo";
    }
}
