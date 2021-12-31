package com.cleanup.todoc.DataBaseInstrumentedTests;

import static org.junit.Assert.assertTrue;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import com.cleanup.todoc.Database.SaveMyTaskDataBase;
import com.cleanup.todoc.model.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ProjectDaoTest {

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

    // DATA SET FOR TEST
    private static final long PROJECT_ID = 1L;

    private static final Project PROJECT_DEMO = new Project(PROJECT_ID, "Projet Tartampion", 0xFFEADAD1);

    @Test
    public void insertAndGetProject() throws InterruptedException {

        // BEFORE : Adding a new project
        this.database.projectDao().insertAllProjects(PROJECT_DEMO);

        // TEST
        List<Project> project = LiveDataTestUtil.getValue(this.database.projectDao().getAllProjects());

        assertTrue(project.size() == 1);

        assertTrue(project.get(0).getName().equals(PROJECT_DEMO.getName()) && project.get(0).getId() == PROJECT_ID);

    }

}