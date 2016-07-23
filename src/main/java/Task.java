import java.util.Date;

/**
 * Created by MiloS on 23.07.2016.
 */
public interface Task {
    /**
     * Gets the name of the task
     * @return the name
     */
    String getTaskName();

    /**
     * Sets the name of the task
     * @param taskName the name
     */
    void setTaskName(String taskName);

    /**
     * Gets the description text of the task
     * @return the description text
     */
    String getTaskText();

    /**
     * Sets the task description text
     * @param taskText the description text
     */
    void setTaskText(String taskText);

    /**
     * Checks whether the task is finished
     * @return true if the task is finished, aflse otherwise
     */
    boolean isFinished();

    /**
     * Marks the task as finished
     * @param finished
     */
    void setFinished(boolean finished);

    /**
     * Gets the finish date of the task
     * @return the date if the task is finished, null otherwise
     */
    Date getDateOfFinished();

    /**
     * Sets the finish date
     * @param dateOfFinished the date to be set
     */
    void setDateOfFinished(Date dateOfFinished);
}
