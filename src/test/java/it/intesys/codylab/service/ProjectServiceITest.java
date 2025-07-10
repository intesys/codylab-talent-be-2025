package it.intesys.codylab.service;

import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.dto.TaskDTO;
import it.intesys.codylab.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class ProjectServiceITest {

    @Autowired
    ProjectService projectService;

    @DisplayName("Ciclo di vita di un progetto")
    @Test
    @Transactional
    void testLifeCycle() {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setCodice("TEST001");
        projectDTO.setNome("TEST001");
        projectDTO.setDescrizione("Test Project 001");
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setCodice("TASK001");
        taskDTO.setNome("TASK001");
        taskDTO.setDescrizione("Test Task 001");
        projectDTO.setTasks(List.of(taskDTO));

        projectDTO = projectService.save(projectDTO);

        assertNotNull(projectDTO);
        assertThat(projectDTO.getId()).isNotNull();
        assertThat(projectDTO.getCodice()).isEqualTo("TEST001");

        Project project = projectService.getProjectById(projectDTO.getId());
        assertNotNull(project);
        assertThat(project.getCodice()).isEqualTo("TEST001");

        projectDTO = new ProjectDTO();
        projectDTO.setCodice("TEST002");
        projectDTO.setNome("TEST002");
        projectDTO.setDescrizione("Test Project 002");
        taskDTO = new TaskDTO();
        taskDTO.setCodice("TASK002");
        taskDTO.setNome("TASK002");
        taskDTO.setDescrizione("Test Task 002");
        projectDTO.setTasks(List.of(taskDTO));

        projectService.save(projectDTO);

        List<ProjectDTO> allProjects = projectService.findAll();
        assertNotNull(allProjects);
        assertThat(allProjects.size()).isEqualTo(2);
        for (ProjectDTO projectDTO1 : allProjects) {
            System.out.println(projectDTO1);
        }

    }

    @Test
    @Transactional
    void findByCodice() {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setCodice("TEST001");
        projectDTO.setNome("TEST001");
        projectDTO.setDescrizione("Test Project 001");
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setCodice("TASK001");
        taskDTO.setNome("TASK001");
        taskDTO.setDescrizione("Test Task 001");
        projectDTO.setTasks(List.of(taskDTO));

        projectDTO = projectService.save(projectDTO);

        projectDTO = projectService.findByCodice("TEST001");
        assertNotNull(projectDTO);
        assertThat(projectDTO.getNome()).isEqualTo("TEST001");

        projectDTO = projectService.findByCodice("TEST000");
        assertNull(projectDTO);

    }

}
