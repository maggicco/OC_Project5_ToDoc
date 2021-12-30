package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.Database.SaveMyTaskDataBase;
import com.cleanup.todoc.Repository.ProjectDataRepository;
import com.cleanup.todoc.Repository.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    // TODO: 30/12/2021  used by private void configureViewModel()  in Main Activity
    // check if useful only for Provider and ItemContentProvider class !!!
    public static TaskDataRepository provideTaskDataSource(Context context) {
        SaveMyTaskDataBase database = SaveMyTaskDataBase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static ProjectDataRepository provideProjectDataSource(Context context) {
        SaveMyTaskDataBase database = SaveMyTaskDataBase.getInstance(context);
        return new ProjectDataRepository(database.projectDao());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository dataSourceItem = provideTaskDataSource(context);
        ProjectDataRepository dataSourceUser = provideProjectDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceItem, dataSourceUser, executor);
    }

}
