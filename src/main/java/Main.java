import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by MiloS on 21.07.2016.
 */
public class Main {

    private static ArrayList<Task> unfinishedTasks = new ArrayList<Task>();
    private static ArrayList<Task> finishedTasks = new ArrayList<Task>();
    private static String fileName = "tasks.json";

    public static void main(String args[]) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Loader loader = new FileLoader();
        loader.setSource(fileName);

        //load tasks from file
        loader.load(unfinishedTasks, finishedTasks);

        Saver toFileSaver = new FileSaver();
        toFileSaver.setDestination(fileName);

        String input = "";


        while(true) {
            //show the main menu
            showMenu();
            try {
                input = reader.readLine();
            }catch (IOException e) {
                e.printStackTrace();
                try {
                    reader.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
                return;
            }

            //parse user input
            Integer option;
            try {
                 option = Integer.parseInt(input);
            }catch (NumberFormatException e) {
                System.out.println("Wrong input, try again");
                continue;
            }

            switch(option) {
                    case 1: {
                        addTask(reader);
                        break;
                    }
                    case 2: {
                        showMenuUnfinished(reader);
                        break;
                    }
                    case 3: {
                        showMenuFinished(reader);
                        break;
                    }
                    case 4: {
                        toFileSaver.save(unfinishedTasks, finishedTasks);
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    default: {
                        System.out.println("Wrong input, try again.");
                        break;
                    }
                }


        }

    }

    /**
     * Shows the menu of finished tasks
     * @param reader the reader for user input
     */
    public static void showMenuFinished(BufferedReader reader) throws IOException {

        while(true) {
            System.out.println("--------------------");
            System.out.println("|FINISHED TASK MENU|");
            System.out.println("--------------------");
            System.out.println("Press -1 to return or specific number to get detail");
            if (finishedTasks.size() == 0) {
                System.out.println("-----------------");
                System.out.println("No finished tasks");
                System.out.println("-----------------");
            } else {
                System.out.println("Finished tasks:");
                System.out.println("---------------");
                for (int i = 0; i < finishedTasks.size(); i++) {
                    System.out.println(i + "." + " " + finishedTasks.get(i).getTaskName());
                }
                System.out.println("---------------");
            }


            //read user input
            String input = "";
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            //parse user input
            Integer option;
            try {
                 option = Integer.parseInt(input);
            }catch(NumberFormatException e) {
                System.out.println("Wrong input, try again");
                continue;
            }

            //user wants to return to upper menu
            if (option == -1) {
                return;
            }

            //user selected to view one of the tasks
            if (option >= 0 && option < finishedTasks.size()) {
                Task task = finishedTasks.get(option);
                showTask(task, option, reader);
            } else {
                System.out.println("Wrong input, try again");
                continue;
            }
        }
    }

    /**
     * Shows the menu of unfinished tasks
     * @param reader the reader for user input
     */
    public static void showMenuUnfinished(BufferedReader reader) {

        while(true) {
            System.out.println("----------------------");
            System.out.println("|UNFINISHED TASK MENU|");
            System.out.println("----------------------");
            System.out.println("Press -1 to return or specific number to get detail");
            if (unfinishedTasks.size() == 0) {
                System.out.println("Unfinished tasks:");
                System.out.println("-------------------");
                System.out.println("No unfinished tasks");
                System.out.println("-------------------");
            } else {
                System.out.println("Unfinished tasks:");
                System.out.println("-----------------");
                for (int i = 0; i < unfinishedTasks.size(); i++) {
                    System.out.println(i + "." + " " + unfinishedTasks.get(i).getTaskName());
                }
                System.out.println("-----------------");
            }

            //read user input
            String input = "";
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            //parse user input
            Integer option;
            try {
                option = Integer.parseInt(input);
            }catch(NumberFormatException e) {
                System.out.println("Wrong input, try again");
                continue;
            }

            //user selecetd to see one of the tasks
            if (option >= 0 && option < unfinishedTasks.size()) {
                Task task = unfinishedTasks.get(option);
                showTask(task, option, reader);
            }
            return;
        }
    }

    /**
     * Shows the task detail
     * @param task the task to be shown
     * @param index the index of the task
     * @param reader the reader for user input
     */
    public static void showTask(Task task, int index, BufferedReader reader) {

        System.out.println("-----------");
        System.out.println("|TASK MENU|");
        System.out.println("-----------");

        if (task.isFinished()) {
            System.out.println("Press -1 to return");
        } else {
            System.out.println("Press -1 to return or 0 to mark as finished");
        }

        //see task detail
        System.out.println("-------------------------------------------");
        System.out.println(task.toString());
        System.out.println("-------------------------------------------");

        //read user input
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //parse user input
        Integer option = -2;
        try {
            option = Integer.parseInt(input);
        }catch(NumberFormatException e) {
            System.out.println("Wrong input, try again");
        }

        if (option == -1) {
            return;
        }

        //mark the task as finished
        if (!task.isFinished() && option == 0) {
            unfinishedTasks.remove(index);
            task.setFinished(true);
            task.setDateOfFinished(new Date());
            finishedTasks.add(task);
        }

        return;
    }

    /**
     * Adds a new task marked as unfinished
     * @param reader the reader for user input
     */
    public static void addTask(BufferedReader reader) {

        Task task = new TaskImpl();
        System.out.println("---------------");
        System.out.println("|ADD TASK MENU|");
        System.out.println("---------------");
        System.out.print("Set task name: ");

        //read user input
        String name = "";
        try {
            name = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        task.setTaskName(name);

        System.out.print("Set task description: ");

        String text = "";
        try {
            text = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        task.setTaskText(text);

        //add the task to unfinished
        unfinishedTasks.add(task);
        System.out.println("Task added");

    }

    /**
     * Show the main menu
     */
    public static void showMenu() {
        System.out.println("-----------");
        System.out.println("|MAIN MENU|");
        System.out.println("-----------");
        System.out.println("Please select an option from 1-4 and press Enter:");
        System.out.println("------------------");
        System.out.println("1 Add task");
        System.out.println("2 Show Unfinished tasks");
        System.out.println("3 Show Finished tasks");
        System.out.println("4 Exit");
        System.out.println("------------------");
    }
}
