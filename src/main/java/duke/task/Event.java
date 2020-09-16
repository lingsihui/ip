package duke.task;

public class Event extends Task {
    public static final int AT_LENGTH = 3;
    public static final int EVENT_LENGTH = 5;
    protected String at;

    public Event(String description,int slashPosition){
        super(description.substring(EVENT_LENGTH,slashPosition));
        this.at = description.substring(slashPosition + AT_LENGTH);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (at:" + at + ")";
    }

    public void printInvalid(){
        System.out.println("OOPS! Description of EVENT cannot be empty!");
    }

    public String getTaskType(){
        return "Event";
    }

    public String getDescription(){
        return this.description;
    }

    public String getAt(){
        return this.at;
    }

    public int getDescriptionLength() {
        return description.length();
    }
}
