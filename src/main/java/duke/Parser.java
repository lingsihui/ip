package duke;

import duke.command.*;

public class Parser {
    public Parser(){
    }

    public Command evaluateInput(String line) throws DukeException {
        String upperLine = line.toUpperCase();
        if(upperLine.startsWith("TODO")){
            return new AddCommand("Todo",line);
        } else if (upperLine.startsWith("LIST")){
            return new ListCommand();
        } else if (upperLine.startsWith("DEADLINE")){
            return new AddCommand("Deadline",line);
        } else if (upperLine.startsWith("EVENT")){
            return new AddCommand("Event",line);
        } else if (upperLine.startsWith("BYE")){
            return new ExitCommand();
        } else if (upperLine.startsWith("DONE")){
            return new DoneCommand(line);
        } else if (upperLine.startsWith("DELETE")){
            return new DeleteCommand(line);
        } else {
            throw new DukeException();
        }
    }
}

