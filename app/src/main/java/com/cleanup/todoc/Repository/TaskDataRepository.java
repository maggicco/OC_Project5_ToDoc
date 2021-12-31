package com.cleanup.todoc.Repository;

import androidx.lifecycle.LiveData;
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

    //  --- Create a Task ---
    public void insertTask(Task task) {

        taskDao.insertTask(task);

    }

    //  ---Delete a Task---
    public void deleteTask(Task task) {

        taskDao.deleteTask(task);

    }
}
