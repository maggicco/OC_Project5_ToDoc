package com.cleanup.todoc.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    // TODO: 22/12/2021 query to past all projects to spinner 
    @Query("SELECT * FROM project")
    LiveData<List<Project>> getAllProjects();

//    @Query("SELECT * FROM project WHERE id = :projectId")
//    LiveData<List<Project>> getProjectsList(long projectId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllProjects(Project... project);

}
