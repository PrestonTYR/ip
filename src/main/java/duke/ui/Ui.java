package duke.ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * A class that deals with all interactions with the user.
 */
public class Ui {
    
    private static final String MESSAGE_GREETING = "Hello~ I'm Duke!\n" + "What can I do for you?";
    private static final String MESSAGE_FAREWELL = "Goodbye~";
    private static final String MESSAGE_DONE = "Nice! I've set this task as done~";
    private static final String MESSAGE_ADD_TASK = "Got it~ I've added this task:";
    private static final String MESSAGE_NUMBER_OF_TASKS = "You now have %d tasks in the list~";
    private static final String MESSAGE_REMOVE_TASK = "Alright~ I've removed this task:";
    private static final String MESSAGE_SEPERATOR = "____________________________________________________________";
    private static final String MESSAGE_LIST = "Here are your tasks~";
    private static final String MESSAGE_FIND = "Here are the matching tasks in your list~";
    
    private Scanner sc;
    
    
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * A function to print the welcome message when starting Duke.
     */
    public String greet() {
        return MESSAGE_GREETING;
    }

    /**
     * A function to print the goodbye message when stopping Duke.
     */
    public String farewell() {
        return (MESSAGE_SEPERATOR + "\n" + MESSAGE_FAREWELL + "\n" + MESSAGE_SEPERATOR);
    }

    /**
     * A function to print the message when marking a task as done.
     * @param doneTask the Task marked as done.
     */
    public String doneText(Task doneTask) {
        return (MESSAGE_SEPERATOR + "\n" + MESSAGE_DONE + "\n" + doneTask + 
                "\n" + MESSAGE_SEPERATOR);
    }

    /**
     * A function to print the message when adding a task to the list.
     * @param addTask the Task added to the list.
     * @param result the TaskList the task is added to.
     */
    public String addTaskText(Task addTask, TaskList result) {
        return (MESSAGE_SEPERATOR + "\n" + MESSAGE_ADD_TASK + "\n" + addTask +
                "\n" + String.format(MESSAGE_NUMBER_OF_TASKS, result.getSize()) + 
                "\n" + MESSAGE_SEPERATOR);
    }

    /**
     * A function to print the message when deleting a task from the list.
     * @param deleteTask the Task deleted from the list.
     * @param result the TaskList the task is deleted from.
     */
    public String deleteTaskText(Task deleteTask, TaskList result) {
       return (MESSAGE_SEPERATOR + "\n" + MESSAGE_REMOVE_TASK + "\n" + deleteTask +
                "\n" + String.format(MESSAGE_NUMBER_OF_TASKS, result.getSize()) +
                "\n" + MESSAGE_SEPERATOR);
    }
    
    /**
     * A function to print all the tasks in the list.
     * @param tasks the TaskList from which all the tasks should be printed from.
     */
    public String listText(TaskList tasks) {
        return MESSAGE_SEPERATOR + "\n" + MESSAGE_LIST + "\n" + tasks.iterateList()
                + "\n" + MESSAGE_SEPERATOR;
        
    }

    public String listRelevantTasks(TaskList tasks, String keyword) {
        return MESSAGE_SEPERATOR + "\n" + MESSAGE_FIND + "\n" + tasks.iterateFind(keyword) +
                "\n" + MESSAGE_SEPERATOR;
    }

    /**
     * A function to print an error message for the user.
     * @param e the error message.
     */
    public String printError(Exception e) {
        return e.toString();
    }

    /**
     * A function to scan the user's next input and convert it to lower case.
     * @return a string representing the user's input.
     */
    public String readCommand() {
        return sc.nextLine().toLowerCase();
    }
    
    
}
