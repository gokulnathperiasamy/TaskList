package com.kpgn.tasklist.test;

import com.kpgn.tasklist.entity.Task;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("unused")
public class TestData {

    private static List<Task> taskList = new ArrayList<>();

    public static List<Task> getTaskListData() {

        Task task1 = new Task();
        task1.setTaskID("Task-01");
        task1.setTaskContent("Book movie ticket for Kings Man - Golden Circle.");
        task1.setRowUpated(1506342572432L);

        Task task2 = new Task();
        task2.setTaskID("Task-02");
        task2.setTaskContent("Submit documentations and mails..");
        task2.setRowUpated(1506342612700L);

        taskList.add(task1);
        taskList.add(task2);

        return taskList;
    }


}
