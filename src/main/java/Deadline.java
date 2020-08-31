public class Deadline extends Task {
    public static final int BY_LENGTH = 2;
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by.substring(BY_LENGTH);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

}
