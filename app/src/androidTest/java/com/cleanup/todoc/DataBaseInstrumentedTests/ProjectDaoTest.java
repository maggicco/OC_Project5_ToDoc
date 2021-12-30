package com.cleanup.todoc.DataBaseInstrumentedTests;

import static org.junit.Assert.assertTrue;
import android.support.test.InstrumentationRegistry;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import com.cleanup.todoc.Database.SaveMyTaskDataBase;
import com.cleanup.todoc.model.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

@RunWith(JUnit4.class)
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
    private static long PROJECT_ID = 1L;

    private static Project PROJECT_DEMO = new Project(PROJECT_ID, "Projet Tartampion", 0xFFEADAD1);

    @Test
    public void insertAndGetUser() throws InterruptedException {

        // BEFORE : Adding a new project
        this.database.projectDao().insertAllProjects(PROJECT_DEMO);

        // TEST
        Project project = LiveDataTestUtil.getValue(this.database.projectDao().getAllProjects());

        assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);

    }

}