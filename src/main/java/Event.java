public class Event extends Task {
    public static final int AT_LENGTH = 2;
    protected String at;

    public Event(String description, String at){
        super(description);
        this.at = at;
    }

    public String getTaskIcon(){
        return "[E]";
    }

    public String getDate(){
        return "(at:" + at.substring(AT_LENGTH) + ")";
    }
}
