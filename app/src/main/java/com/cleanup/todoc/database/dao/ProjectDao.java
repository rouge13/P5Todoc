package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.cleanup.todoc.model.Project;

import java.util.List;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
@Dao
public interface ProjectDao {

        // Get all projects
        @Query("SELECT * FROM Project")
        LiveData<List<Project>> getProjects();

//        @Query("SELECT * FROM project WHERE id = :projectId")
//        LiveData<Project> getProject(long projectId);

//        @Insert
//        void insertProject(Project projects);

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void createProject(Project project);

        @Query("SELECT * FROM Project WHERE id = :projectId")
        LiveData<Project> getProject(long projectId);


}
