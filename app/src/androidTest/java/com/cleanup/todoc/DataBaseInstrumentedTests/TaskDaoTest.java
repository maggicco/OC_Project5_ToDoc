package com.cleanup.todoc.DataBaseInstrumentedTests;

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
import java.util.Date;
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

    public static long date = new Date().getTime();

    private static Task TASK_ONE = new Task(1, 1L,"Task 1", date);

    private static Task TASK_TWO = new Task(2,1L,"Task 2", date);

    private static Task TASK_THREE = new Task(3, 1L, "Task 3", date);

    // DATA SET FOR TEST
    private static long PROJECT_ID = 1L;

    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Projet Tartampion", 0xFFEADAD1);

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

//    public void insertAndDeleteItem() throws InterruptedException {
//
//
//        // BEFORE : Adding demo user & demo item. Next, get the item added & delete it.
//
//
//        this.database.userDao().createUser(USER_DEMO);
//
//
//        this.database.itemDao().insertItem(NEW_ITEM_PLACE_TO_VISIT);
//
//
//        Item itemAdded = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID)).get(0);
//
//
//        this.database.itemDao().deleteItem(itemAdded.getId());
//
//
//        //TEST
//
//
//        List<Item> items = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID));
//
//
//        assertTrue(items.isEmpty());
//
//
//    }
//
//
//    @Test
//
//
//    public void insertAndUpdateItem() throws InterruptedException {
//
//
//        // BEFORE : Adding demo user & demo items. Next, update item added & re-save it
//
//
//        this.database.userDao().createUser(USER_DEMO);
//
//
//        this.database.itemDao().insertItem(NEW_ITEM_PLACE_TO_VISIT);
//
//
//        Item itemAdded = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID)).get(0);
//
//
//        itemAdded.setSelected(true);
//
//
//        this.database.itemDao().updateItem(itemAdded);
//
//
//
//        //TEST
//
//
//        List<Item> items = LiveDataTestUtil.getValue(this.database.itemDao().getItems(USER_ID));
//
//
//        assertTrue(items.size() == 1 && items.get(0).getSelected());
//
//    }

}
