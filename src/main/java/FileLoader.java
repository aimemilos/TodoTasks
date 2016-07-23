import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MiloS on 23.07.2016.
 * This class loads the task form given source file
 */
public class FileLoader implements Loader {

    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setSource(String name) {
        setFileName(name);
    }

    public void load(Collection<Task> unfinished, Collection<Task> finished) {
        JSONParser parser = new JSONParser();

        try {
            JSONObject allTasks = (JSONObject) parser.parse(new FileReader(fileName));
            JSONArray arrayOfTasks = (JSONArray) allTasks.get("tasks");
            JSONObject singleTask;

            Iterator<JSONObject> iterator = arrayOfTasks.iterator();

            Task task;

            while (iterator.hasNext()) {
                singleTask = iterator.next();
                task = new TaskImpl();
                task.setTaskName((String) singleTask.get("name"));
                task.setTaskText((String) singleTask.get("text"));
                String stringDate = (String) singleTask.get("date");

                if(stringDate.equals("null")) {
                    task.setDateOfFinished(null);
                } else {
                    SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
                    Date date = null;
                    try {
                        date = format.parse(stringDate);
                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }
                    task.setDateOfFinished(date);
                }

                if (((String) singleTask.get("finished")).equals("T")) {
                    task.setFinished(true);
                    finished.add(task);
                } else {
                    task.setFinished(false);
                    unfinished.add(task);
                }

            }

        }catch (FileNotFoundException f) {
            return;
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("The input file " + fileName + " is corrupted. Try to delete it. Exiting\n");
            System.exit(1);
        }

        return;
    }
}
