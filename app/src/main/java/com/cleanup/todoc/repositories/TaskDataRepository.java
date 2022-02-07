package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
public class TaskDataRepository {
    private final TaskDao taskDao;

    public TaskDataRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // --- GET ---
    public LiveData<List<Task>> getTask(long taskId){ return this.taskDao.getTask(taskId); }
    public LiveData<List<Task>> getTasks() {return this.taskDao.getTasks();}

    // --- CREATE ---
    public void createTask(Task task){ taskDao.createTask(task); }

    // --- DELETE ---
    public void deleteTask(Task task){ taskDao.deleteTask(task); }
}
