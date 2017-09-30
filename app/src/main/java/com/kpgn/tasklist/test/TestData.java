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
        task1.setTaskContent("Add new items to Task List!");
        task1.setRowUpated(1506342572432L);

        taskList.add(task1);

        return taskList;
    }


}
