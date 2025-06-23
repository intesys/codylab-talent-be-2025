package it.intesys.codylab.service;

import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.mapper.TaskMapper;
import it.intesys.codylab.model.Project;
import it.intesys.codylab.model.Task;
import it.intesys.codylab.model.User;
import it.intesys.codylab.repository.ProjectRepository;
import it.intesys.codylab.repository.TaskRepository;
import it.intesys.codylab.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository, TaskMapper taskMapper, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
        this.userRepository = userRepository;
    }
    public List<TasksApiDTO> getAllTasks() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false)
                .map(taskMapper::toTaskApiDTO)
                .toList();
    }

    public TasksApiDTO saveTask(TasksApiDTO tasksApiDTO) {
        // 1. Converte il DTO in entity Task usando il mapper
        Task task = taskMapper.toTaskModel(tasksApiDTO);

        // 2. Controlla se nel DTO c'è un progetto collegato (progettoId non nullo)
        if (tasksApiDTO.getProgettoId() != null) {
            // 3. Cerca nel database il Project con quell'id
            Project project = projectRepository.findById(tasksApiDTO.getProgettoId())
                    .orElseThrow(() -> new RuntimeException("Project non trovato"));
            // 4. Imposta il Project trovato nell'entity Task
            task.setProject(project);
        } else {
            // 5. Se il progettoId non c'è, lancia un'eccezione perché è obbligatorio
            throw new RuntimeException("progettoId è obbligatorio");
        }

        Task savedTask = taskRepository.save(task);
        return taskMapper.toTaskApiDTO(savedTask);
    }

//    public TaskDTO saveTask(TaskDTO taskDTO) {
//        // Recupera il progetto associato dalla db, per impostare il riferimento
//        Project project = projectRepository.findById(taskDTO.getProjectId())
//                .orElseThrow(() -> new RuntimeException("Project not found"));
//
//        Task task = taskMapper.toEntity(taskDTO);
//        task.setProject(project);
//
//        Task savedTask = taskRepository.save(task);
//        return taskMapper.toDTO(savedTask);
//    }
//
//    @Transactional
//    public TaskDTO assignUserToTask(Long userId, Long taskId) {
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new RuntimeException("Task not found"));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Relazione bidirezionale
//        if (!task.getUsers().contains(user)) {
//            task.getUsers().add(user);
//        }
//
//        if (!user.getTasks().contains(task)) {
//            user.getTasks().add(task);
//        }
//
//        userRepository.save(user);
//        taskRepository.save(task);
//
//        return taskMapper.toDTO(task);
//    }



}
