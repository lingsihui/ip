public class Todo extends Task {
    public static final int TODO_LENGTH = 4;

    public Todo(String description){
        super(description.substring(TODO_LENGTH));
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
