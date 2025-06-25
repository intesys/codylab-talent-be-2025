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
import org.springframework.transaction.annotation.Transactional;
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

    public TasksApiDTO updateTask(Long taskId, TasksApiDTO tasksApiDTO) {
        // 1. Recupera il Task esistente dal database
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task non trovato"));

        // 2. Aggiorna i campi del Task esistente con i valori del DTO
        existingTask.setNome(tasksApiDTO.getNome());
        existingTask.setDescrizione(tasksApiDTO.getDescrizione());
        existingTask.setDataInizio(tasksApiDTO.getDataInizio());
        existingTask.setDurata(tasksApiDTO.getDurata());
        existingTask.setCodice(tasksApiDTO.getCodice());

        // 3. Se nel DTO c'è un progettoId, aggiorna il riferimento al progetto
        if (tasksApiDTO.getProgettoId() != null) {
            Project project = projectRepository.findById(tasksApiDTO.getProgettoId())
                    .orElseThrow(() -> new RuntimeException("Project non trovato"));
            existingTask.setProject(project);
        } else {
            // 4. Se il progettoId non c'è, lancia un'eccezione perché è obbligatorio
            throw new RuntimeException("progettoId è obbligatorio");
        }

        // 5. Salva le modifiche e ritorna il DTO aggiornato
        Task updatedTask = taskRepository.save(existingTask);
        return taskMapper.toTaskApiDTO(updatedTask);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        System.out.println("=== INIZIO DELETE TASK ID: " + taskId + " ===");

        // Verifica esistenza
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Task non trovato");
        }

        // Prova con query nativa
        System.out.println("Eseguo query nativa...");
        int deletedRows = taskRepository.deleteByIdCustom(taskId);
        System.out.println("Righe cancellate: " + deletedRows);

        taskRepository.flush();

        boolean existsAfter = taskRepository.existsById(taskId);
        System.out.println("Task esiste dopo: " + existsAfter);

        System.out.println("=== FINE DELETE ===");
    }

//    // Metodo per assegnare un utente a un task
//
//    public TasksApiDTO assignUserToTask(Long userId, Long taskId) {
//        Task task = taskRepository.findById(taskId)
//                .orElseThrow(() -> new RuntimeException("Task non trovato"));
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User non trovato"));
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
//        Task savedTask = taskRepository.save(task);
//
//        return taskMapper.toTaskApiDTO(savedTask);
//    }
}