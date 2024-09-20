package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;
import se.edu.streamdemo.task.TaskComparator;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);

        System.out.println("Printing deadlines ...");
        printSortedDeadlinesByStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        ArrayList<Task> filter = filterTaskByStream(tasksData, "7");
        printAllData(filter);
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
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printSortedDeadlinesByStream(ArrayList<Task> tasksData) {
        tasksData.stream()
                .filter(t -> t instanceof Deadline)
                .sorted((t1, t2) -> t1.getDescription().compareTo(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskByStream(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filtered = (ArrayList<Task>) tasksData.stream()
                .filter(t -> t.getDescription().contains(filterString))
                .collect(toList());
        return filtered;
    }
}
