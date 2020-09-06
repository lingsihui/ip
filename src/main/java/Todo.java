public class Todo extends Task {
    public static final int TODO_LENGTH = 5;

    public Todo(String description){
        super(description.substring(TODO_LENGTH));
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public void printInvalid(){
        System.out.println("OOPS! Description of TODO cannot be empty!");
    }
}
