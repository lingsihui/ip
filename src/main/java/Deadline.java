public class Deadline extends Task {
    private static final int BY_LENGTH = 3;
    public static final int DEADLINE_LENGTH = 9;
    protected String by;

    public Deadline(String description, int slashPosition){
        super(description.substring(DEADLINE_LENGTH,slashPosition));
        this.by = description.substring(slashPosition + BY_LENGTH);
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    @Override
    public void printInvalid(){
        System.out.println("OOPS! Description of DEADLINE cannot be empty!");
    }
}
