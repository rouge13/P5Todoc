package com.cleanup.todoc;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import com.cleanup.todoc.database.SaveMyTaskDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
@RunWith(AndroidJUnit4.class)
public class TaskDaoTest {

//        // DATA SET FOR TEST
        private static final long PROJECT_ID = 4L;
        private static final Project PROJECT_DEMO = new Project(PROJECT_ID, "Projet Champion", 0xFFEADAD1);
        // DATA SET FOR TEST
        private static final Task TASK_DEMO = new Task(1,PROJECT_ID, "TestInstrument", 1643104690);

//        @Test
//        public void insertAndGetProject() throws InterruptedException {
//                // BEFORE : Adding a new project
//                this.database.projectDao().createProject(PROJECT_DEMO);
//                // TEST
//                Project project = LiveDataTestUtil.getValue(this.database.projectDao().getProject(PROJECT_ID));
//                assertTrue(project.getName().equals(PROJECT_DEMO.getName()) && project.getId() == PROJECT_ID);
//        }

        @Test
        public void insertAndGetTask() throws InterruptedException {
                // BEFORE : Adding a new task
//                this.database.projectDao().createProject(PROJECT_DEMO);
                this.database.taskDao().createTask(TASK_DEMO);
                // TEST
                List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTask(PROJECT_ID));
                assertEquals(1, tasks.size());
        }

        @Test
        public void getTasksWhenNoTaskInserted() throws InterruptedException {
                // TEST
                List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTask(PROJECT_ID));
                assertTrue(tasks.isEmpty());
        }

        @Test
        public void insertAndDeleteItem() throws InterruptedException {

//                this.database.projectDao().createProject(PROJECT_DEMO);
                this.database.taskDao().createTask(TASK_DEMO);

                // TEST
                List<Task> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getTask(PROJECT_ID));
                assertEquals(1, tasks.size());
                this.database.taskDao().deleteTask(tasks.remove(0));

                //TEST
                assertTrue(tasks.isEmpty());


        }

        // FOR DATA
        private SaveMyTaskDatabase database;

        @Rule
        public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

        @Before
        public void initDb() throws Exception {
            this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(),
                    SaveMyTaskDatabase.class)
                    .allowMainThreadQueries()
                    .build();
//            this.database = SaveMyTaskDatabase.getInstance(InstrumentationRegistry.getContext());
            this.database.projectDao().createProject(PROJECT_DEMO);
        }

        @After
        public void closeDb() throws Exception {
            database.close();
        }



}
