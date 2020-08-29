public class Todo extends Task {
    public static final int TODO_LENGTH = 4;

    public Todo(String description){
        super(description.substring(TODO_LENGTH));
    }

    public String getTaskIcon(){
        return "[T]";
    }
}
