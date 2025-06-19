package it.intesys.codylab.service;

import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.repository.TaskRepository;
import it.intesys.codylab.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, UserRepository userRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.taskMapper = taskMapper;
    }

    public TaskDTO create(TaskDTO dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        Task task = taskMapper.toEntity(dto);
        task.setProject(project);

        if (dto.getUserIds() != null && !dto.getUserIds().isEmpty()) {
            List<User> users = (List<User>) userRepository.findAllById(dto.getUserIds());
            task.setUsers(users);
        }

        Task saved = taskRepository.save(task);
        return taskMapper.toDTO(saved);
    }

    public TaskDTO addUsersToTask(Long taskId, List<Long> userIds) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        List<User> users = (List<User>) userRepository.findAllById(userIds);

        task.getUtenti().addAll(users);

        for (User user : users) {
            user.getTasks().add(task);
        }

        Task saved = taskRepository.save(task);
        return taskMapper.toDTO(saved);
    }

    public List<TaskDTO> showTasks() {
        List<Task> tasks = (List<Task>) taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::toDTO)
                .toList();
    }

}

