package com.cleanup.todoc.DataBaseInstrumentedTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import com.cleanup.todoc.Database.SaveMyTaskDataBase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

    // FOR DATA
    private SaveMyTaskDataBase database;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {

        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),

                SaveMyTaskDataBase.class)

                .allowMainThreadQueries()

                .build();

    }

    @After
    public void closeDb() throws Exception {

        database.close();

    }

    //public static long date = new Date().getTime();

    private static final Task TASK_ONE = new Task(1, 1L,"Task 1", 1);

    private static final Task TASK_TWO = new Task(2,1L,"Task 2", 2);

    private static final Task TASK_THREE = new Task(3, 1L, "Task 3", 3);

    // DATA SET FOR TEST
    private static final long PROJECT_ID = 1L;

    private static final Project PROJECT_DEMO = new Project(PROJECT_ID, "Projet Tartampion", 0xFFEADAD1);

    @Test
    public void getTaskWhenEmpty() throws InterruptedException {

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());

        assertTrue(tasks.isEmpty());

    }

    @Test
    public void insertGetDeleteTask() throws InterruptedException {

        // BEFORE : Adding demo project & demo task
        this.database.projectDao().insertAllProjects(PROJECT_DEMO);

        this.database.taskDao().insertTask(TASK_ONE);

        this.database.taskDao().insertTask(TASK_TWO);

        this.database.taskDao().insertTask(TASK_THREE);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());

        assertTrue(tasks.size() == 3);

        this.database.taskDao().deleteTask(TASK_THREE);

        List<Task> task = LiveDataTestUtil.getValue(this.database.taskDao().getTaskList());

        assertTrue(task.size() == 2);

    }

    @Test
    public void getAzTaskListTest() throws InterruptedException {

        // BEFORE : Adding demo project & demo task
        this.database.projectDao().insertAllProjects(PROJECT_DEMO);

        this.database.taskDao().insertTask(TASK_ONE);

        this.database.taskDao().insertTask(TASK_TWO);

        this.database.taskDao().insertTask(TASK_THREE);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getAzTaskList());

        assertTrue(tasks.size() == 3);

        assertEquals(tasks.get(0).getName(), TASK_ONE.getName());
        assertEquals(tasks.get(2).getName(), TASK_THREE.getName());

    }

    @Test
    public void getZaTaskListTest() throws InterruptedException {

        // BEFORE : Adding demo project & demo task
        this.database.projectDao().insertAllProjects(PROJECT_DEMO);

        this.database.taskDao().insertTask(TASK_ONE);

        this.database.taskDao().insertTask(TASK_TWO);

        this.database.taskDao().insertTask(TASK_THREE);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getZaTaskList());

        assertTrue(tasks.size() == 3);

        assertEquals(tasks.get(0).getName(), TASK_THREE.getName());
        assertEquals(tasks.get(2).getName(), TASK_ONE.getName());

    }

    @Test
    public void getYoungestTaskListTest() throws InterruptedException {

        // BEFORE : Adding demo project & demo task
        this.database.projectDao().insertAllProjects(PROJECT_DEMO);

        this.database.taskDao().insertTask(TASK_ONE);

        this.database.taskDao().insertTask(TASK_TWO);

        this.database.taskDao().insertTask(TASK_THREE);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getYoungestTaskList());

        assertTrue(tasks.size() == 3);

        assertEquals(tasks.get(0).getCreationTimestamp(), TASK_ONE.getCreationTimestamp());
        assertEquals(tasks.get(2).getCreationTimestamp(), TASK_THREE.getCreationTimestamp());

    }

    @Test
    public void getOldestTaskListTest() throws InterruptedException {

        // BEFORE : Adding demo project & demo task
        this.database.projectDao().insertAllProjects(PROJECT_DEMO);

        this.database.taskDao().insertTask(TASK_ONE);

        this.database.taskDao().insertTask(TASK_TWO);

        this.database.taskDao().insertTask(TASK_THREE);

        // TEST
        List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getOldestTaskList());

        assertTrue(tasks.size() == 3);

        assertEquals(tasks.get(0).getCreationTimestamp(), TASK_THREE.getCreationTimestamp());
        assertEquals(tasks.get(2).getCreationTimestamp(), TASK_ONE.getCreationTimestamp());

    }


}
