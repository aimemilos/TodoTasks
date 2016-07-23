import java.util.ArrayList;

/**
 * Created by MiloS on 23.07.2016.
 */
public interface Saver {
    /**
     * Sets the destination to save the tasks to
     * @param name the name of output location
     */
    void setDestination(String name);

    /**
     * Saves the tasks
     * @param unfinished the list of unfinished tasks
     * @param finished the list of finished tasks
     */
    void save(ArrayList<Task> unfinished, ArrayList<Task> finished);
}
