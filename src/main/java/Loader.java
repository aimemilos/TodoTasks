import java.util.Collection;

/**
 * Created by MiloS on 23.07.2016.
 */
public interface Loader {
    /**
     * Sets the source of input
     * @param name the name of input location
     */
    void setSource(String name);

    /**
     * Loads the finished and unfinished tasks to their collections
     * @param unfinished the collection of unfinished tasks
     * @param finished the collection of finished tasks
     */
    void load(Collection<Task> unfinished, Collection<Task> finished);
}
