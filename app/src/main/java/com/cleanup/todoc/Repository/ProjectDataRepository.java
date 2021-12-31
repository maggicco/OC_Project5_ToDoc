package com.cleanup.todoc.Repository;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.Dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectDataRepository {

    private final ProjectDao projectDao;

    public ProjectDataRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    // --- Get Project ---
    public LiveData<List<Project>> getAllProjects() {

        return this.projectDao.getAllProjects();

    }

}
