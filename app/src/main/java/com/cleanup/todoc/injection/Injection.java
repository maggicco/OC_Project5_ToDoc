package com.cleanup.todoc.injection;

import android.content.Context;

import com.cleanup.todoc.Database.SaveMyTaskDataBase;
import com.cleanup.todoc.Repository.ProjectDataRepository;
import com.cleanup.todoc.Repository.TaskDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    public static TaskDataRepository provideItemDataSource(Context context) {
        SaveMyTaskDataBase database = SaveMyTaskDataBase.getInstance(context);
        return new TaskDataRepository(database.taskDao());
    }

    public static ProjectDataRepository provideUserDataSource(Context context) {
        SaveMyTaskDataBase database = SaveMyTaskDataBase.getInstance(context);
        return new ProjectDataRepository(database.projectDao());
    }

    public static Executor provideExecutor(){ return Executors.newSingleThreadExecutor(); }

    public static ViewModelFactory provideViewModelFactory(Context context) {
        TaskDataRepository dataSourceItem = provideItemDataSource(context);
        ProjectDataRepository dataSourceUser = provideUserDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceItem, dataSourceUser, executor);
    }

}
