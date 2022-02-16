package com.cleanup.todoc.database;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.Executors;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class SaveMyTaskDatabase extends RoomDatabase {
    // --- SINGLETON ---
    private static volatile SaveMyTaskDatabase INSTANCE;
    private static final String DB_NAME = "MyDatabase.db";

    // --- DAO ---
    public abstract TaskDao taskDao();
    public abstract ProjectDao projectDao();

    // --- INSTANCE ---
    public static SaveMyTaskDatabase getInstance(Context context) {

        if (INSTANCE == null) {
            synchronized (SaveMyTaskDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SaveMyTaskDatabase.class, DB_NAME)
                            .addCallback(prepopulateDatabase())
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Project[] projects = Project.getAllProjects();
                for (Project project : projects) {
                    ContentValues contentValuesProject = new ContentValues();
                    contentValuesProject.put("id", project.getId());
                    contentValuesProject.put("name", project.getName());
                    contentValuesProject.put("color", project.getColor());
                    db.insert("Project", OnConflictStrategy.IGNORE, contentValuesProject);

                }
                final long TASK_ID = 1;

                ContentValues contentValuesTask = new ContentValues();
                contentValuesTask.put("id", TASK_ID);
                contentValuesTask.put("project_id", 1L);
                contentValuesTask.put("name", "TestTaskAdded");
                contentValuesTask.put("creationTimestamp", 1643104690);
                db.insert("Task", OnConflictStrategy.IGNORE, contentValuesTask);
            }
        };
    }
}
