package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);

        printDeadlinesUsingStreams(tasksData);

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
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

}
