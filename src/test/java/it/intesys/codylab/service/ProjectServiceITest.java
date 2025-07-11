package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectFilterApiDTO;
import it.intesys.codylab.api.model.ProjectsApiDTO;
import it.intesys.codylab.api.model.TasksApiDTO;
import it.intesys.codylab.model.Project;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setCode("TEST001");
        projectsApiDTO.setName("TEST001");
        projectsApiDTO.setDescription("Test Project 001");
        TasksApiDTO tasksApiDTO = new TasksApiDTO();
        tasksApiDTO.setCode("TASK001");
        tasksApiDTO.setName("TASK001");
        tasksApiDTO.setDescription("Test Task 001");

        projectsApiDTO = projectService.createProject(projectsApiDTO);

        assertNotNull(projectsApiDTO);
        assertThat(projectsApiDTO.getId()).isNotNull();
        assertThat(projectsApiDTO.getCode()).isEqualTo("TEST001");

        ProjectsApiDTO project = projectService.getProjectById(projectsApiDTO.getId());
        assertNotNull(project);
        assertThat(project.getCode()).isEqualTo("TEST001");

        projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setCode("TEST002");
        projectsApiDTO.setName("TEST002");
        projectsApiDTO.setDescription("Test Project 002");
        tasksApiDTO = new TasksApiDTO();
        tasksApiDTO.setCode("TASK002");
        tasksApiDTO.setName("TASK002");
        tasksApiDTO.setDescription("Test Task 002");

        projectService.createProject(projectsApiDTO);

        ProjectFilterApiDTO filter = new ProjectFilterApiDTO();
        Page<ProjectsApiDTO> allProjects = projectService.getProjects(filter, 0, 10, "id");
        assertNotNull(allProjects);
        assertThat(allProjects.getTotalElements()).isEqualTo(2);
        for (ProjectsApiDTO projectApiDTO : allProjects.getContent()) {
            System.out.println(projectApiDTO);
        }
    }
    @Test
    @Transactional
    void createProject() {
        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setCode("TEST001");
        projectsApiDTO.setName("TEST001");
        projectsApiDTO.setDescription("Test Project 001");

        ProjectsApiDTO createdProject = projectService.createProject(projectsApiDTO);
        assertNotNull(createdProject);
        assertThat(createdProject.getId()).isNotNull();
        assertThat(createdProject.getCode()).isEqualTo("TEST001");

        ProjectsApiDTO retrievedProject = projectService.getProjectById(createdProject.getId());
        assertNotNull(retrievedProject);
        assertThat(retrievedProject.getCode()).isEqualTo("TEST001");
    }

    @Test
    @Transactional
    void findByCodice() {
        ProjectsApiDTO projectsApiDTO = new ProjectsApiDTO();
        projectsApiDTO.setCode("TEST001");
        projectsApiDTO.setName("TEST001");
        projectsApiDTO.setDescription("Test Project 001");
        TasksApiDTO tasksApiDTO = new TasksApiDTO();
        tasksApiDTO.setCode("TASK001");
        tasksApiDTO.setName("TASK001");
        tasksApiDTO.setDescription("Test Task 001");

        projectsApiDTO = projectService.createProject(projectsApiDTO);

        ProjectsApiDTO retrievedProject = projectService.getProjectById(projectsApiDTO.getId());
        assertNotNull(retrievedProject);
        assertThat(retrievedProject.getName()).isEqualTo("TEST001");

        ProjectFilterApiDTO filter = new ProjectFilterApiDTO();
        filter.setProjectCodes(List.of("TEST000"));
        Page<ProjectsApiDTO> projects = projectService.getProjects(filter, 0, 10, "id");
        assertThat(projects.getTotalElements()).isEqualTo(0);
}
}