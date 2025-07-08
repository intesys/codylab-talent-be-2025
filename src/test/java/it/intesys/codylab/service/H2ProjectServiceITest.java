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
@ActiveProfiles("h2")
public class H2ProjectServiceITest extends ProjectServiceITest {

}
