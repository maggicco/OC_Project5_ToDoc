package com.cleanup.todoc;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;

import com.cleanup.todoc.Database.SaveMyTaskDataBase;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

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

}
