package duke.storage;
import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * A class to save tasks into the file and to load tasks from the file.
 */
public class Storage {

    /**
     * The file location where Duke's list of tasks if stored.
     */
    private String filepath;
    
    public Storage(String filepath) {
        this.filepath = filepath;
    }


    /**
     * A function to load all the tasks saved in the file.
     * @param taskList the TaskList where all the tasks should be loaded into.
     */
    public void pullList(TaskList taskList) {
        try {
            File file = new File(filepath);
            file.createNewFile();
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                Task temp;
                String line = sc.nextLine();
                String[] nextTaskArr = line.split("\\|");
                int nextTaskLength = nextTaskArr.length;
                String nextTaskType = nextTaskArr[0].strip();

                if (nextTaskType.equals("T")) {
                    if (nextTaskLength != 3) { 
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Todo(nextTaskArr[2].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    taskList.add(temp);
                } else if (nextTaskType.equals("D")) {
                    if (nextTaskLength != 4) {
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Deadline(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    taskList.add(temp);
                } else if (nextTaskType.equals("E")) {
                    if (nextTaskLength != 4) {
                        throw new DukeException("Your data might be corrupted~");
                    }
                    temp = new Event(nextTaskArr[2].strip(), nextTaskArr[3].strip());
                    if (nextTaskArr[1].strip().equals("1")) {
                        temp.setDone();
                    }
                    taskList.add(temp);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * A function to save all the tasks into the file.
     * @param taskList the TaskList whose tasks should be stored into the file.
     */
    public void storelist(TaskList taskList) {
        try {
            String seperator = " | ";
            FileWriter fileWriter = new FileWriter(filepath);

            for (int i = 0; i < taskList.getSize(); i++) {
                Task temp = taskList.get(i);
                String doneStatus = "0";
                if (temp.checkDone()) {
                    doneStatus = "1";
                }

                if (temp instanceof Todo) {
                    fileWriter.write("T" + seperator + doneStatus + seperator + 
                            temp.getTaskName() + "\n");
                } else if (temp instanceof Deadline) {
                    Deadline tempDeadline = (Deadline) temp; 
                    fileWriter.write("D" + seperator + doneStatus + seperator +
                            tempDeadline.getTaskName() + seperator + tempDeadline.getDeadlineDate());
                } else if (temp instanceof Event) {
                    Event tempEvent = (Event) temp;
                    fileWriter.write("E" + seperator + doneStatus + seperator +
                            tempEvent.getTaskName() + seperator + tempEvent.getEventDate());
                }
            }
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
