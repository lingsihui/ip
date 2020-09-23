package duke;

import duke.command.Command;

import java.io.IOException;

/**
 * Represents the main class. A <code>Duke</code> object contains the
 * main method.
 */
public class Duke {
    public static final String FILE_PATH = "data/duke.txt";

    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    private Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        try{
            tasks = new TaskList(storage.load());
        } catch (DukeException e){
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e){
            ui.showErrorInLoadingFile();
            tasks = new TaskList();
        }
    }

    private void run() {
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
    /**
     * Load the file contents and create a new Task list by initializing the Duke Object.
     * Start the program by running the duke method.
     *
     * @param args
     */
    public static void main(String[] args){
        new Duke(FILE_PATH).run();
    }
}