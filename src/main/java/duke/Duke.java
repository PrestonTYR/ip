package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    
    
    static final String filepath = "duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    
    public Duke (String filepath) {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            storage.pullList(tasks);
        } catch (Exception e) {
            ui.printError(e);
        }
    }
    
    public void run() {
        ui.greet();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String nextCommand = ui.readCommand();
                Command c = Parser.parse(nextCommand);
                c.execute(tasks, ui, storage);
                isRunning = c.continueRunning();
            } catch (DukeException e) {
                ui.printError(e);
            }
        }
    }
    
    public static void main (String[] args) {
        new Duke(filepath).run();
    }

    
    
    

//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
    
}