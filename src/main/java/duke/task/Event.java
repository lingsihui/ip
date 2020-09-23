package duke.task;

/**
 * Represents an event task in Duke. An <code>event Task</code> object corresponds to
 * any tasks with a description, a boolean isDone to mark whether task is completed and an
 * At description of when the event is going to occur.
 * e.g., <code>Event meeting /at sunday</code>
 */
public class Event extends Task {
    public static final int AT_LENGTH = 3;
    public static final int EVENT_LENGTH = 5;
    protected String at;

    /**
     * Constructor for  Event()
     * Initialise description of task, isDone boolean and description of at.
     * Default isDone is false.
     *
     * @param description task description. e.g. meeting
     * @param slashPosition index of the slash position.e.g. sunday.
     */
    public Event(String description,int slashPosition){
        super(description.substring(EVENT_LENGTH,slashPosition));
        this.at = description.substring(slashPosition + AT_LENGTH);
    }
    /**
     * Returns the strings of the task icon, status icon,description of the task
     * and description of at when task is printed.
     *
     * @return String "[E]" + status icon + task description + at description.
     */
    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
    /**
     * Returns at description for the event.
     * Getter.
     *
     * @return String at. e.g. sunday.
     */
    public String getAt(){
        return this.at;
    }
    /**
     * Returns the type of the task as Event task.
     * Getter.
     *
     * @return String Event.
     */
    public String getType(){
        return "Event";
    }

}
