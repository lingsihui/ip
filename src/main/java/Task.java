public class Task {
    private String list;
    private int done;

    public Task() {
        this(null,0);
    }

    public Task(String list, int done){
        this.list = list;
        this.done = done;
    }

}
