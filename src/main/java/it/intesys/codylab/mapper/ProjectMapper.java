package it.intesys.codylab.mapper;

import it.intesys.codylab.model.Project;
import it.intesys.codylab.dto.ProjectDTO;
import it.intesys.codylab.utility.DateUtils;

public class ProjectMapper {

    public static ProjectDTO toDTO(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectDTO(
                project.getId(),
                project.getCodice(),
                project.getNome(),
                project.getDescrizione(),
                DateUtils.formatDateToItalian(project.getDataInizio()),
                project.getDurata()
        );
    }

    public static Project toEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }
        return new Project(
                projectDTO.getId(),
                projectDTO.getCodice(),
                projectDTO.getNome(),
                projectDTO.getDescrizione(),
                DateUtils.parseItalianDate(projectDTO.getDataInizio()),
                projectDTO.getDurata()
        );
    }
}