package com.cleanup.todoc.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM task")
    LiveData<List<Task>> getTaskList();

    @Query("SELECT * FROM task ORDER BY name ASC")
    LiveData<List<Task>> getAzTaskList();

    @Query("SELECT * FROM task ORDER BY name DESC")
    LiveData<List<Task>> getZaTaskList();

    @Query("SELECT * FROM task ORDER BY creationTimestamp DESC")
    LiveData<List<Task>> getOldestTaskList();

    @Query("SELECT * FROM task ORDER BY creationTimestamp ASC")
    LiveData<List<Task>> getYoungestTaskList();

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

}
