package com.cleanup.todoc.database.dao;
import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long createTask(Task task);

    @Query("SELECT * FROM Task WHERE project_id = :projectId")
    LiveData<List<Task>> getTask(long projectId);

    @Query("SELECT * FROM Task WHERE project_id = :projectId")
    Cursor getTasksWithCursor(long projectId);

    @Update
    int updateTask(Task task);

    @Delete
    int deleteTask(Task task);
}
