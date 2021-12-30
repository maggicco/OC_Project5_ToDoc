package com.cleanup.todoc.Repository;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.Dao.TaskDao;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskDataRepository {

    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    //  --- Get List of Tasks ---
    public LiveData<List<Task>> getTaskList() {

        return this.taskDao.getTaskList();

    }

    //  --- Get List of Tasks ---
    public LiveData<List<Task>> getAzTaskList() {

        return this.taskDao.getAzTaskList();

    }

    //  --- Get List of Tasks ---
    public LiveData<List<Task>> getZaTaskList() {

        return this.taskDao.getZaTaskList();

    }

    //  --- Get List of Tasks ---
    public LiveData<List<Task>> getOldestTaskList() {

        return this.taskDao.getOldestTaskList();

    }

    //  --- Get List of Tasks ---
    public LiveData<List<Task>> getYoungestTaskList() {

        return this.taskDao.getYoungestTaskList();

    }

    //  --- Create a Task ---
    public void insertTask(Task task) {

        taskDao.insertTask(task);

    }

    //  ---Delete a Task---
    public void deleteTask(Task task) {

        taskDao.deleteTask(task);

    }
}
