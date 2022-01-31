package com.cleanup.todoc.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.ProjectDataRepository;
import com.cleanup.todoc.repositories.TaskDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

import io.reactivex.rxjava3.annotations.Nullable;


/**
 * Created by Julien HAMMER - Apprenti Java with openclassrooms on .
 */
public class TaskViewModel extends ViewModel {
    //-- REPOSITORIES --
    private final ProjectDataRepository projectDataSource;
    private final TaskDataRepository taskDataSource;
    private final Executor executor;

    //-- DATA --
    @Nullable
    private LiveData<List<Project>> mProjects;

    public TaskViewModel(ProjectDataRepository projectDataSource, TaskDataRepository taskDataSource, Executor executor) {
        this.projectDataSource = projectDataSource;
        this.taskDataSource = taskDataSource;
        this.executor = executor;
    }

    public void init() {
        if (mProjects == null) {
            mProjects = projectDataSource.getProjects();
        }
    }

    // -------------
    // FOR PROJECTS
    // -------------
        public LiveData<List<Project>> getProjects(){
            return mProjects;
        }

    // -------------
    // FOR TASK
    // -------------

    public LiveData<List<Task>> getTask(long projectId) {
        return taskDataSource.getTask(projectId);
    }

    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> taskDataSource.deleteTask(task));
    }

    public void updateTask(Task task) {
        executor.execute(() -> taskDataSource.updateTask(task));
    }

}
