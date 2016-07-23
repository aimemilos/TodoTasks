import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by MiloS on 23.07.2016.
 * This class saves the tasks to given destination file
 */
public class FileSaver implements Saver {

    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setDestination(String name) {
        setFileName(name);
    }

    public void save(ArrayList<Task> unfinished, ArrayList<Task> finished) {
        PrintWriter printWriter = null;

        JSONObject allTasks = new JSONObject();
        JSONArray arrayOfTasks = new JSONArray();
        JSONObject singleTask;

        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file, false);

            printWriter = new PrintWriter(fileWriter);

            for(int i = 0; i < unfinished.size(); i++) {
                Task task = unfinished.get(i);
                singleTask = new JSONObject();
                singleTask.put("finished", "F");
                singleTask.put("name", task.getTaskName());
                singleTask.put("text", task.getTaskText());
                singleTask.put("date", "null");
                arrayOfTasks.add(singleTask);
            }

            for(int i = 0; i < finished.size(); i++) {
                Task task = finished.get(i);
                singleTask = new JSONObject();
                singleTask.put("finished", "T");
                singleTask.put("name", task.getTaskName());
                singleTask.put("text", task.getTaskText());
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                singleTask.put("date",
                        dateFormat.format(
                                task.getDateOfFinished()));

                arrayOfTasks.add(singleTask);
            }
            allTasks.put("tasks", arrayOfTasks);
            fileWriter.write(allTasks.toJSONString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}
