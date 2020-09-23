package duke;

import duke.command.*;

/**
 * Represent a parser to parse the user input.
 */
public class Parser {
    public Parser(){
    }
    /**
     * Returns a command class corresponding to the user input.
     * If the user input does not associate with any command, a Duke exception is thrown.
     *
     * @param line  user input.
     * @return Command.
     * @throws DukeException  If user input does not start with any commands.
     */
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
        } else if (upperLine.startsWith("FIND")){
            return new FindCommand(line);
        } else if (upperLine.startsWith("DATE")){
            return new DateCommand(line);
        } else {
            throw new DukeException();
        }
    }
}

