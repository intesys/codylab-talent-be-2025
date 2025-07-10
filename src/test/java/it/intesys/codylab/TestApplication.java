package it.intesys.codylab;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class TestApplication {

    @Test
    public void contextLoads() {
        Assertions.assertTrue(true, "Context loads successfully");
    }


}
