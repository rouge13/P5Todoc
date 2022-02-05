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
    // For Tasks sorted
//    public LiveData<List<Task>> getTasksOrderByNameASC() {
//        return taskDataSource.getTasksOrderByNameASC();
//    }
//    public LiveData<List<Task>> getTasksOrderByNameDesc() {
//        return taskDataSource.getTasksOrderByNameDesc();
//    }
//    public LiveData<List<Task>> getTasksOrderByCreationTimeRecentestFirst() {
//        return taskDataSource.getTasksOrderByCreationTimeRecentestFirst();
//    }
//    public LiveData<List<Task>> getTasksOrderByCreationTimeOldestFirst() {
//        return taskDataSource.getTasksOrderByCreationTimeOldestFirst();
//    }

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

    public LiveData<List<Task>> getTasks() {
//        switch (choiceSelected) {
//            case "NoSorting":
//                return taskDataSource.getTasks();
//            case "OrderByNameAsc":
//                return taskDataSource.getTasksOrderByNameASC();
//            case "OrderByNameDesc":
//                return taskDataSource.getTasksOrderByNameDesc();
//            case "OrderByCreationTimeOldestFirst":
//                return taskDataSource.getTasksOrderByCreationTimeOldestFirst();
//            case "OrderByCreationTimeRecentestFirst":
//                return taskDataSource.getTasksOrderByCreationTimeRecentestFirst();
//        }
        return taskDataSource.getTasks();
    }

    public void createTask(Task task) {
        executor.execute(() -> {
            taskDataSource.createTask(task);
        });
    }

    public void deleteTask(Task task) {
        executor.execute(() -> taskDataSource.deleteTask(task));
    }

//    public void updateTask(Task task) {
//        executor.execute(() -> taskDataSource.updateTask(task));
//    }

    /**
     * List of all possible sort methods for task
     */
    private enum SortMethod {
        /**
         * Sort alphabetical by name
         */
        ALPHABETICAL,
        /**
         * Inverted sort alphabetical by name
         */
        ALPHABETICAL_INVERTED,
        /**
         * Lastly created first
         */
        RECENT_FIRST,
        /**
         * First created first
         */
        OLD_FIRST,
        /**
         * No sort
         */
        NONE
    }

}
