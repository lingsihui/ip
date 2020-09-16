package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    public boolean getIsDone(){
        return isDone;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public String getBy(){
        return " ";
    }
    public String getAt(){
        return " ";
    }


    @Override
    public String toString(){
        return getStatusIcon() + description;
    }
    public abstract String getTaskType();

    public abstract void printInvalid();

    public String getDescription(){
        return this.description;
    }

    public int getDescriptionLength() {
        return description.length();
    }
}
