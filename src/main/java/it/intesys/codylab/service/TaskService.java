package it.intesys.codylab.service;

import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

//  findById
    public Task findById(Long id){
        return taskRepository.findById(id);
    }

//  findAll
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

//  addTask
    public void save(Task task){
        taskRepository.save(task);
    }

//  updateTask
    public void update(Long id, Task task){
        taskRepository.update(id, task);
    }
}
