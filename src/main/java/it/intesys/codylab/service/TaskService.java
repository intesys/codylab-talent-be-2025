package it.intesys.codylab.service;

import it.intesys.codylab.model.Task;
import it.intesys.codylab.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task findById(Long id){
        return taskRepository.findById(id);
    }
}
