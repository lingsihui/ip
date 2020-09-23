package duke;

import duke.command.Command;

import java.io.IOException;

public class Duke {
    public static final String FILE_PATH = "data/duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e){
            ui.showFileNotFoundError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String line = ui.readInput();;
                ui.showLine();
                Command c = new Parser().evaluateInput(line);
                c.executeCommand(tasks, ui, storage);
                isExit = c.isExit();
            }catch(DukeException e){
                ui.printInvalidInputMessage();
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args){
        new Duke(FILE_PATH).run();
    }
}