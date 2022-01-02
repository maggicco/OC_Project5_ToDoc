package com.cleanup.todoc.injection;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.cleanup.todoc.Repository.ProjectDataRepository;
import com.cleanup.todoc.Repository.TaskDataRepository;
import com.cleanup.todoc.ui.TaskViewModel;
import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    // REPOSITORIES
    private final TaskDataRepository taskDataSource;
    private final ProjectDataRepository projectDataSource;
    private final Executor executor;

    public ViewModelFactory(TaskDataRepository taskDataSource, ProjectDataRepository projectDataSource, Executor executor) {
        this.taskDataSource = taskDataSource;
        this.projectDataSource = projectDataSource;
        this.executor = executor;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TaskViewModel.class)) {

            return (T) new TaskViewModel(taskDataSource, projectDataSource, executor);

        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }


}
