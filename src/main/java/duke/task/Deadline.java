package duke.task;

public class Deadline extends Task {
    private static final int BY_LENGTH = 3;
    public static final int DEADLINE_LENGTH = 8;
    protected String by;

    public Deadline(String description, int slashPosition){
        super(description.substring(DEADLINE_LENGTH,slashPosition));
        this.by = description.substring(slashPosition + BY_LENGTH);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    public String getDescription(){
        return this.description;
    }
    public String getType(){
        return "Deadline";
    }

    public String getBy(){
        return this.by;
    }

    public int getDescriptionLength() {
        return description.length();
    }
}
