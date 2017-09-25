package com.kpgn.tasklist.entity;


public class Task {

    private String taskID;
    private String taskContent;
    private long rowUpated;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public long getRowUpated() {
        return rowUpated;
    }

    public void setRowUpated(long rowUpated) {
        this.rowUpated = rowUpated;
    }

}
