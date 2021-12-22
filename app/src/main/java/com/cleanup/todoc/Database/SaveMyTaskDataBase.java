package com.cleanup.todoc.Database;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.Dao.ProjectDao;
import com.cleanup.todoc.Dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class SaveMyTaskDataBase extends RoomDatabase {

    /**
     * SINGLETON
     */
    private static volatile SaveMyTaskDataBase INSTANCE;

    /**
    ** DAO
     **/
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    /**
     * @INSTANCE
     */
    
    public static SaveMyTaskDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SaveMyTaskDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyTaskDataBase.class, "TaskDataBase.db")
                            //.addCallback(pr)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
