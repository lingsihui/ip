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

    public void markAsDone(){
        this.isDone = true;
    }

    @Override
    public String toString(){
        return getStatusIcon() + description;
    }

    public abstract void printInvalid();

    public int getDescriptionLength() {
        return description.length();
    }
}
