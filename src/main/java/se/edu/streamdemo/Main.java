package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        printDeadlines(tasksData);
        System.out.println("===============");
        printDeadlinesUsingStreams(tasksData);

        ArrayList<Task> filteredList = filterByStringUsingStream(tasksData, "11");
        System.out.println("Printing filtered tasks ...");
        printAllData(filteredList);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines (using stream): " + countDeadlinesUsingStream(tasksData));

    }
    private static int countDeadlinesUsingStream(ArrayList<Task> taskData) {
        int count = (int) taskData.stream()
                .filter((Task t) -> t instanceof Deadline)
                .count();

        return count;
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing all tasks using iteration...");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printAllDataUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing all data using streams ...");
        tasksData.stream()
                .forEach(System.out::println);

    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Print deadline using iterations...");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Print sorted deadlines using streams");
        tasksData.stream()
                .filter((Task t) -> t instanceof Deadline)
                .sorted((t1,t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredList = new ArrayList<>();
        for (Task t  : tasksData) {
            if (t.getDescription().contains(filterString)) {
                filteredList.add(t);
            }
        }
        return filteredList;
    }

    public static ArrayList<Task> filterByStringUsingStream(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasksData.stream()
                .filter((Task t) -> t.getDescription().contains(filterString))
                .collect(toList());

        return filteredList;
    }
}
