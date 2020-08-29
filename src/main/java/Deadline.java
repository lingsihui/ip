public class Deadline extends Task {
    public static final int BY_LENGTH = 2;
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String getTaskIcon(){
        return "[D]";
    }

    public String getDate(){
        return "(by:" + by.substring(BY_LENGTH) + ")";
    }

}
