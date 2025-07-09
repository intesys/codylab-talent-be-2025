package it.intesys.codylab.service;

import it.intesys.codylab.api.model.ProjectsApiDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ProjectServiceITest {
    @Autowired
    ProjectService projectService;

    @Test
    void getProjectById() {
       List <ProjectsApiDTO> projectDTOList = projectService.findAllPaginated(0, 10)
                .stream()
                .map(project -> projectService.getProjectById(project.getId()))
                .toList();

        projectDTOList.forEach(projectDTO -> {
            System.out.println("Project ID: " + projectDTO.getId());
            System.out.println("Project Name: " + projectDTO.getNome());
            System.out.println("Project Code: " + projectDTO.getCodice());
            System.out.println("Project Description: " + projectDTO.getDescrizione());
            System.out.println("Project Start Date: " + projectDTO.getDataInizio());
        });
    }
}
