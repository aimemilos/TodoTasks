import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by MiloS on 21.07.2016.
 */
public class TaskImpl implements Task{
    private String taskName;
    private String taskText;
    private boolean finished;
    private Date dateOfFinished;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getDateOfFinished() {
        return dateOfFinished;
    }

    public void setDateOfFinished(Date dateOfFinished) {
        this.dateOfFinished = dateOfFinished;
    }

    @Override
    public String toString() {
        String string = "Name: " + taskName + "\n" + "Text: " + taskText;

        if(finished) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            string += "\nFinished: " + dateFormat.format(dateOfFinished);
        }
        return string;
    }
}
