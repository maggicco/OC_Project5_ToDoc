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

    @Insert
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

}
