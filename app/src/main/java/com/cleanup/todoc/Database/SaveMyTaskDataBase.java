package com.cleanup.todoc.Database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.Dao.ProjectDao;
import com.cleanup.todoc.Dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.Executors;

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
     * INSTANCE
     */
    public synchronized static SaveMyTaskDataBase getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = buildDataBase(context);
        }
        return INSTANCE;
    }

    /**
     * Instance method
     * @param context
     * @return ROOM.dataBaseBuilder()
     */
    public static SaveMyTaskDataBase buildDataBase(final Context context) {
        return Room.databaseBuilder(context,
                SaveMyTaskDataBase.class, "TaskDataBase.db")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).projectDao().insertAllProjects(Project.getAllProjects());
                            }
                        });
                    }
                })
                .build();
    }

}
