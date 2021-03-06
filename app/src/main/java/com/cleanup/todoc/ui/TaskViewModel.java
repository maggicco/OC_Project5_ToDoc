package com.cleanup.todoc.ui;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.todoc.Repository.ProjectDataRepository;
import com.cleanup.todoc.Repository.TaskDataRepository;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import java.util.List;
import java.util.concurrent.Executor;

public class TaskViewModel extends ViewModel {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;

    private final ProjectDataRepository projectDataSource;

    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<List<Project>> currentProject;

    public TaskViewModel(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    public void init() {

        if (this.currentProject != null) {

            return;

        }

        currentProject = projectDataSource.getAllProjects();

    }

    /**
     *  For Project
     */
    public LiveData<List<Project>> getAllProjects() {

        return this.currentProject;

    }

    /**
     *  For Task
     */
    public LiveData<List<Task>> getTaskList() {

        return taskDataSource.getTaskList();

    }

    /**
     * Create=Insert Task
     */
    public void insertTask(Task task) {

        executor.execute(() -> taskDataSource.insertTask(task));

    }

    /**
     * Delete Task
     */
    public void deleteTask(Task task) {

        executor.execute(() -> taskDataSource.deleteTask(task));

    }

}
